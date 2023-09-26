package com.fiddovea.fiddovea.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fiddovea.fiddovea.appUtils.AppUtils;
import com.fiddovea.fiddovea.data.models.*;
import com.fiddovea.fiddovea.data.repository.CustomerRepository;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.FiddoveaException;
import com.fiddovea.fiddovea.exceptions.ProductAlreadyAdded;
import com.fiddovea.fiddovea.exceptions.UserNotFoundException;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.fiddovea.fiddovea.appUtils.AppUtils.JSON_PATCH_PATH_PREFIX;
import static com.fiddovea.fiddovea.data.models.Role.CUSTOMER;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.*;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.PROFILE_UPDATE_SUCCESSFUL;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.*;

@Slf4j
@Service
@AllArgsConstructor
public class FiddoveaCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MailService mailService;
    private final ProductService productService;
    private final TokenService tokenService;
    private final ChatService chatService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();
        if(checkRegisterEmail(email)) throw new BadCredentialsException(EMAIL_ALREADY_EXIST.getMessage());

        Customer customer = customerMapper(email,password);


        Customer savedCustomer = customerRepository.save(customer);

        RegisterResponse response = new RegisterResponse();
        response.setMessage(REGISTRATION_SUCCESSFUL.name());
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        return verifyLoginDetails(email, password);
    }

    @Override
    public GetResponse getCustomerById(String id)throws UserNotFoundException {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        Customer customer = foundCustomer.orElseThrow(
                ()->new UserNotFoundException(USER_NOT_FOUND.getMessage())
        );
        return buildUserResponse(customer);
    }


    private static GetResponse buildUserResponse(Customer savedCustomer) {
        Address address = savedCustomer.getAddress();
        String addressString = (address != null) ? address.toString() : "";

        return GetResponse.builder()
                .fullName(getFullName(savedCustomer))
                .lga(addressString)
                .houseNumber(addressString)
                .build();
    }


    private static String getFullName(Customer savedCustomer) {
        StringBuilder fullNameBuilder = new StringBuilder();

        if (savedCustomer.getFirstName() != null) {
            fullNameBuilder.append(savedCustomer.getFirstName());
        }

        if (savedCustomer.getLastName() != null) {
            if (fullNameBuilder.length() > 0) {
                fullNameBuilder.append(" ");
            }
            fullNameBuilder.append(savedCustomer.getLastName());
        }
        return fullNameBuilder.toString();
    }



    public UpdateCustomerResponse updateProfile(UpdateCustomerRequest updateCustomerRequest, String userId) throws JsonPatchException {
        Customer customer = findById(userId);
        ModelMapper modelMapper = new ModelMapper();
        JsonPatch updatePatch = buildUpdatePatch(updateCustomerRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode customerNode = objectMapper.convertValue(customer, JsonNode.class);
        JsonNode updatedNode = updatePatch.apply(customerNode);
        Customer updatedCustomer = objectMapper.convertValue(updatedNode, Customer.class);

//        Address userAddress = customer.getAddress();
//        modelMapper.map(updateCustomerRequest, userAddress);
//        customer.setAddress(userAddress);
        customerRepository.save(updatedCustomer);
        modelMapper.map(updateCustomerRequest, customer);

        JsonPatch updatedPatch = buildUpdatePatch(updateCustomerRequest);
        return applyPatch(updatedPatch, customer);
    }


    private JsonPatch buildUpdatePatch(UpdateCustomerRequest updateCustomerRequest) {
        Field[] fields = updateCustomerRequest.getClass().getDeclaredFields();
        List<ReplaceOperation> operations = Arrays.stream(fields)
                .filter(field -> validateField(updateCustomerRequest, field))
                .map(field -> buildReplaceOperation(updateCustomerRequest, field))
                .toList();
        List<JsonPatchOperation> patchOperations = new ArrayList<>(operations);
        return new JsonPatch(patchOperations);
    }


    private boolean validateField(UpdateCustomerRequest updateCustomerRequest, Field field) {
        List<String> excludedFields = List.of("gender", "houseNumber", "lga", "street", "state");
        field.setAccessible(true);

        try {
            return field.get(updateCustomerRequest) != null && !excludedFields.contains(field.getName());
        } catch (IllegalAccessException e) {
            throw new FiddoveaException(e.getMessage());
        }
    }

    private ReplaceOperation buildReplaceOperation(UpdateCustomerRequest updateCustomerRequest, Field field) {
        field.setAccessible(true);
        try {
            String path = JSON_PATCH_PATH_PREFIX + field.getName();
            JsonPointer pointer = new JsonPointer(path);
            var value = field.get(updateCustomerRequest);
            TextNode node = new TextNode(value.toString());
            return new ReplaceOperation(pointer, node);
        } catch (Exception exception) {
            throw new FiddoveaException(exception.getMessage());
        }
    }


    private UpdateCustomerResponse applyPatch(JsonPatch updatePatch, Customer customer) throws JsonPatchException {
        ObjectMapper objectMapper = new ObjectMapper();
        //1. Convert user to JsonNode
        JsonNode userNode = objectMapper.convertValue(customer, JsonNode.class);

        //2. Apply patch to JsonNode from step 1
        JsonNode updatedNode = updatePatch.apply(userNode);
        //3. Convert updatedNode to user
        customer = objectMapper.convertValue(updatedNode, Customer.class);
        log.info("user-->{}", customer);
        //4. Save updatedUser from step 3 in the DB
        var savedUser= customerRepository.save(customer);
        log.info("user-->{}", savedUser);
        return new UpdateCustomerResponse(PROFILE_UPDATE_SUCCESSFUL.name());

    }



    private LoginResponse verifyLoginDetails(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null){
            if(customer.getPassword().equals(password)){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setMessage(WELCOME_BACK.name());
                return loginResponse;
            }else throw new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage());
        }else throw new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage());
    }

//    @Override
//    public Optional<Customer> findById(String id) {
//        Optional<Customer> customer = customerRepository.findById(id);
//        if(customer.isPresent()){
//            Customer foundCustomer = customer.get();
//            return Optional.of(foundCustomer);
//        }
//        return Optional.empty();
//    }

    @Override
    public WishListResponse addToWishList(WishListRequest wishListRequest) {
        String customerId = wishListRequest.getCustomerId();
        String productId = wishListRequest.getProductId();

        Customer foundCustomer = findById(customerId);

        List<Product> productList = foundCustomer.getWishList();
        checkProductListForDuplicate(productId, productList);

        Product selectedProduct = productService.findById(productId);
        foundCustomer.getWishList().add(selectedProduct);

        customerRepository.save(foundCustomer);

        WishListResponse wishListResponse = new WishListResponse();
        wishListResponse.setMessage(PRODUCT_ADDED_TO_WISH_LIST.name());


        return wishListResponse;
    }

    private static void checkProductListForDuplicate(String productId, List<Product> products) {
        for (Product newProduct: products) {
            if(newProduct.getProductId().equals(productId)) {
                throw new ProductAlreadyAdded(PRODUCT_ADDED.name());
            }
        }
    }

    @Override
    public List<Product> viewWishList(String customerId) {
        Customer customer = findById(customerId);
        List<Product> wishList = customer.getWishList();
        return wishList;
    }

    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) {
        String productId = addToCartRequest.getProductId();
        String customerId = addToCartRequest.getCustomerId();

        Customer foundCustomer = findById(customerId);

        List<Product> productList = foundCustomer.getCart().getProducts();
        checkProductListForDuplicate(productId, productList);

        Product selectedProduct = productService.findById(productId);
        foundCustomer.getCart().getProducts().add(selectedProduct);

        customerRepository.save(foundCustomer);

        AddToCartResponse addToCartResponse = new AddToCartResponse();
        addToCartResponse.setMessage(PRODUCT_ADDED_TO_CART.name());
        return addToCartResponse;
    }

    @Override
    public String forgetPassword(ForgetPasswordRequest request) {
        String userEmail = request.getEmail();
        Customer foundCustomer = findByEmail(userEmail);
        forgetPasswordMail(foundCustomer);
        return IF_YOUR_EMAIL_IS_REGISTERED_YOU_WILL_GET_A_MESSAGE_FROM_US.name();
    }

    @Override
    public RemoveProductResponse removeFromCart(RemoveProductRequest removeProductRequest) {
        String customerId = removeProductRequest.getUserId();
        String productId = removeProductRequest.getProductId();

        Customer foundCustomer = findById(customerId);

        List<Product> productList = foundCustomer.getCart().getProducts();
        productList.removeIf(foundProduct -> foundProduct.getProductId().equals(productId));

        customerRepository.save(foundCustomer);

        RemoveProductResponse removeProductResponse = new RemoveProductResponse();
        removeProductResponse.setMessage(ITEM_REMOVED.name());

        return removeProductResponse;
    }

    @Override
    public RemoveProductResponse removeFromWishList(RemoveProductRequest request) {
        String customerId = request.getUserId();
        String productId = request.getProductId();

        Customer foundCustomer = findById(customerId);
        List<Product> productList = foundCustomer.getWishList();
        productList.removeIf(foundProduct -> foundProduct.getProductId().equals(productId));
        customerRepository.save(foundCustomer);

        RemoveProductResponse removeProductResponse = new RemoveProductResponse();
        removeProductResponse.setMessage(ITEM_REMOVED.name());
        return removeProductResponse;
    }
    @Override
    public List<Product> searchProduct(String productName) {
        return productService.findProductByName(productName);
    }
    @Override
    public ProductReviewResponse reviewProduct(ProductReviewRequest productReviewRequest, String customerId) {
        Customer customer = findById(customerId);
        String product = productReviewRequest.getProductId();
        Review review = new Review();
        review.setReviewAuthor(productReviewRequest.getReviewAuthor());
        review.setReviewContent(productReviewRequest.getReviewContent());
        review.setProductRatings(productReviewRequest.getProductRatings());
        review.setReviewDate(productReviewRequest.getReviewDate());
        return null;
    }

    @Override
    public Chat chatCustomerCare(String senderId) {
        return chatService.chatCustomerCare(senderId);
    }

    @Override
    public MessageResponse message(SendMessageRequest sendMessageRequest, String chatId) {
        return chatService.message(sendMessageRequest, chatId);
    }


//    @Override
//    public List<Product> filterByPrice(double minPrice, double maxPrice) {
//
//
//        return null;
//    }

    @Override
    public Customer findById(String customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND.getMessage()));
        return foundCustomer;
    }

    private void forgetPasswordMail(Customer foundCustomer) {
        JavaMailerRequest request = new JavaMailerRequest();
        request.setSubject("Password Rest");
        request.setMessage(foundCustomer.getPassword());
        request.setTo(foundCustomer.getEmail());
        mailService.send(request);
    }


    private Customer customerMapper(String email, String password) {
       Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setRole(CUSTOMER);
        Cart cart = new Cart();
        customer.setCart(cart);
        return customer;
    }


    private boolean checkRegisterEmail(String email){
        if(!AppUtils.verifyEmail(email)) throw new BadCredentialsException(INVALID_EMAIL.getMessage());
        Customer foundCustomer =customerRepository.findByEmail(email);
        if (foundCustomer != null) return true;
        return false;
    }
    private Customer findByEmail(String email){
        Customer foundCustomer = customerRepository.findByEmail(email);
        if (foundCustomer != null) return foundCustomer;
        else throw new UserNotFoundException(USER_NOT_FOUND.getMessage());
    }
}
