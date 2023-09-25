package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.appUtils.AppUtils;
import com.fiddovea.fiddovea.data.models.*;
import com.fiddovea.fiddovea.data.repository.ProductRepository;
import com.fiddovea.fiddovea.data.repository.VendorRepository;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.DeleteProductResponse;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.ProductResponse;
import com.fiddovea.fiddovea.dto.response.VendorRegistrationResponse;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.FiddoveaException;
import com.fiddovea.fiddovea.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ProductResponse addProduct(ProductRequest productRequest, String vendorId) {
        // TODO THERE IS A BU HERE> THE VENDOR LIST SET
        Vendor vendor = vendorRepository
                                  .findById(vendorId)
                                  .orElseThrow(() -> new FiddoveaException(USER_NOT_FOUND.name()));
        Product savedProduct = productService.addNewProduct(productRequest, vendorId);

        vendor.getProductList().add(savedProduct);
        vendorRepository.save(vendor);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage(PRODUCT_ADD_MESSAGE);
        return productResponse;
    }

    @Override
    public DeleteProductResponse deleteProduct(String vendorId, String productId) {
        Vendor foundVendor = findVendorById(vendorId);

        List<Product> productList = foundVendor.getProductList();
        log.info("------------------------>>>" + productList.get(0));
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
