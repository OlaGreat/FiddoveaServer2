package com.fiddovea.fiddovea.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fiddovea.fiddovea.appUtils.AppUtils;
import com.fiddovea.fiddovea.data.models.*;
import com.fiddovea.fiddovea.data.repository.VendorRepository;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.FiddoveaException;
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
import java.time.LocalDateTime;
import java.util.*;

import static com.fiddovea.fiddovea.appUtils.AppUtils.JSON_PATCH_PATH_PREFIX;
import static com.fiddovea.fiddovea.appUtils.AppUtils.PRODUCT_ADD_MESSAGE;
import static com.fiddovea.fiddovea.data.models.Role.VENDOR;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.*;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.*;

@Service
@AllArgsConstructor
@Slf4j
public class FiddoveaVendorService implements VendorService {

    private final VendorRepository vendorRepository;
    private final ProductService productService;
    private final AdminService adminService;


    private final NotificationService notificationService;

    private final ChatService chatService;


    @Override
    public VendorRegistrationResponse register(VendorRegistrationRequest request) {
        String password = request.getPassword();
        String email = request.getEmail().toLowerCase();
        if(checkRegisterEmail(email)) throw new BadCredentialsException(EMAIL_ALREADY_EXIST.getMessage());
        Vendor vendor = new Vendor();
        vendor.setEmail(email);
        vendor.setPassword(password);
        vendor.setBusinessType(BusinessType.valueOf(request.getBusinessType().toUpperCase()));
        vendor.setCompanyPhoneNumber(request.getCompanyPhoneNumber());
        vendor.setCompanyRcNumber(request.getCompanyRcNumber());
        vendor.setProductType(ProductType.valueOf(request.getProductType().toUpperCase()));
        Address address = new Address();
        address.setLga(request.getLga());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setState(request.getState());
        vendor.setCompanyAddress(address);
        vendor.setRole(VENDOR);

        Vendor savedVendor = vendorRepository.save(vendor);

        verifyVendor(savedVendor);

        VendorRegistrationResponse response = new VendorRegistrationResponse();
        response.setMessage(REGISTRATION_SUCCESSFUL.name() +"_"+ PENDING_BUSINESS_DETAIL_VERIFICATION.name());

        return response;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();

        return verifyLoginDetails(email, password);
    }

    @Override
    public GetResponse getVendorById(String id){
        Optional<Vendor> foundVendor = vendorRepository.findById(id);
        Vendor vendor = foundVendor.orElseThrow(
                ()-> new UserNotFoundException(USER_NOT_FOUND.getMessage())
        );

        return buildVendorResponse(vendor);
    }


    private GetResponse buildVendorResponse(Vendor savedVendor) {
        return GetResponse.builder()
                .fullName(getVendorFullName(savedVendor))
                .phoneNumber(savedVendor.getPhoneNumber())
                .email(savedVendor.getEmail())
                .build();
    }
    private static String getVendorFullName(Vendor savedVendor){
        var fullName = new StringBuilder();

        if (savedVendor.getFirstName() != null){
            fullName.append(savedVendor.getFirstName());
        }
        if (savedVendor.getLastName() != null){
            if (fullName.length() > 0){
                fullName.append(" ");
            }
            fullName.append(savedVendor.getLastName());
        }
        return fullName.toString();
    }


    @Override
    public UpdateVendorResponse updateProfile(UpdateVendorRequest updateVendorRequest, String id) throws JsonPatchException {
        Vendor vendor = findVendorById(id);
        ModelMapper modelMapper = new ModelMapper();
        JsonPatch updatePatch = buildUpdatePatch(updateVendorRequest);
        log.info("patch-->{}", updatePatch);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode vendorNode = objectMapper.convertValue(vendor, JsonNode.class);
        log.info("node--->{}", vendorNode);
        JsonNode updatedNode = updatePatch.apply(vendorNode);
        Vendor updatedCustomer = objectMapper.convertValue(updatedNode, Vendor.class);

        vendorRepository.save(updatedCustomer);

        modelMapper.map(updateVendorRequest, vendor);

        JsonPatch updatedPatch = buildUpdatePatch(updateVendorRequest);
        return applyPatch(updatedPatch, vendor);
    }


    private JsonPatch buildUpdatePatch(UpdateVendorRequest updateVendorRequest) {
        Field[] fields = updateVendorRequest.getClass().getDeclaredFields();
        List<ReplaceOperation> operations = Arrays.stream(fields)
                .filter(field -> {
//                    System.out.println(field);
                    return validateField(updateVendorRequest, field);
                })
                .map(field -> buildReplaceOperation(updateVendorRequest, field))
                .toList();
        List<JsonPatchOperation> patchOperations = new ArrayList<>(operations);
        return new JsonPatch(patchOperations);
    }

    private ReplaceOperation buildReplaceOperation(UpdateVendorRequest updateVendorRequest, Field field) {
        field.setAccessible(true);
        try {
            String path = JSON_PATCH_PATH_PREFIX + field.getName();
            JsonPointer pointer = new JsonPointer(path);
            var value = field.get(updateVendorRequest);
            TextNode node = new TextNode(value.toString());
            return new ReplaceOperation(pointer, node);
        } catch (Exception exception) {
            throw new FiddoveaException(exception.getMessage());
        }
    }

    private boolean validateField(UpdateVendorRequest updateVendorRequest, Field field) {
        List<String> excludedFields = List.of("companyAddress");
        field.setAccessible(true);
        try {
            return field.get(updateVendorRequest) != null && !excludedFields.contains(field.getName());
        } catch (IllegalAccessException e) {
            throw new FiddoveaException(e.getMessage());
        }
    }


    private UpdateVendorResponse applyPatch(JsonPatch updatePatch, Vendor vendor) throws JsonPatchException {
        ObjectMapper objectMapper = new ObjectMapper();
        //1. Convert user to JsonNode
        JsonNode vendorNode = objectMapper.convertValue(vendor, JsonNode.class);

        //2. Apply patch to JsonNode from step 1
        JsonNode updatedNode = updatePatch.apply(vendorNode);
        //3. Convert updatedNode to user
        vendor = objectMapper.convertValue(updatedNode, Vendor.class);
//        log.info("user-->{}", vendor);
        //4. Save updatedUser from step 3 in the DB
        var savedUser= vendorRepository.save(vendor);
        log.info("user-->{}", savedUser);
        return new UpdateVendorResponse(PROFILE_UPDATE_SUCCESSFUL.name());

    }


    @Override
    public ProductResponse addProduct(ProductRequest productRequest, String vendorId) {
        Vendor vendor = vendorRepository
                                  .findById(vendorId)
                                  .orElseThrow(() -> new FiddoveaException(USER_NOT_FOUND.name()));
        Product savedProduct = productService.addNewProduct(productRequest, vendorId);

        vendor.getProductList().add(savedProduct);

        sendNotificationToVendor(vendor, savedProduct);
        vendorRepository.save(vendor);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage(PRODUCT_ADD_MESSAGE);
        return productResponse;
    }


    private void sendNotificationToVendor(Vendor vendor, Product product) {

        Notification notification = new Notification();
        List<String> message = Collections.singletonList("Your product '" + product.getProductName() + "' has been added successfully.");
//        notification.setMessages();
//        notification.setTimestamp(LocalDateTime.now());
        notification.setUserId(vendor.getId());

        notificationService.addNotification(product.getProductId(), message);
    }


    @Override
    public DeleteProductResponse deleteProduct(String vendorId, String productId) {
        Vendor foundVendor = findVendorById(vendorId);

        List<Product> productList = foundVendor.getProductList();
        productList.removeIf(foundProduct -> foundProduct.getProductId().equals(productId));
        productService.deleteProduct(productId);

        vendorRepository.save(foundVendor);

        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setMessage(PRODUCT_DELETED_SUCCESSFULLY.name());

        return deleteProductResponse;
    }

    @Override
    public List<Product> viewMyProduct(String vendorId) {
        Vendor foundVendor = findVendorById(vendorId);
        List<Product> myProductList = foundVendor.getProductList();
        return myProductList;
    }

    @Override
    public List<Product> viewOrder(String vendorId) {
        Vendor foundVendor = findVendorById(vendorId);
        return foundVendor.getOrders();
    }

    @Override
    public Chat chatCustomerCare(String senderId) {
        return chatService.chatCustomerCare(senderId);
    }

    @Override
    public MessageResponse message(SendMessageRequest sendMessageRequest, String chatId) {
        return chatService.message(sendMessageRequest, chatId);
    }


    private Vendor findVendorById(String vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND.getMessage()));
    }

    private LoginResponse verifyLoginDetails(String email, String password) {
        Optional<Vendor> vendor = vendorRepository.readByEmail(email);
        if (vendor.isPresent()){
            if(vendor.get().getPassword().equals(password)){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setMessage(WELCOME_BACK.name());
                return loginResponse;
            }else throw new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage());
        }else throw new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage());
    }

    private boolean checkRegisterEmail(String  email){
        if(!AppUtils.verifyEmail(email)) throw new BadCredentialsException(INVALID_EMAIL.getMessage());
        Optional<Vendor> vendor = vendorRepository.readByEmail(email);
        return vendor.isPresent();
    }

    private void verifyVendor(Vendor newVendor){
        adminService.pendingVerify(newVendor);
    }
}
