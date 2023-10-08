package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Chat;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.data.models.Vendor;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface VendorService {
    VendorRegistrationResponse register(VendorRegistrationRequest request);
    VendorLoginResponse login(LoginRequest loginRequest);

    GetResponse getVendorById(String id);
    UpdateVendorResponse updateProfile(UpdateVendorRequest updateVendorRequest, HttpServletRequest request) throws JsonPatchException;
    ProductResponse addProduct(ProductRequest productRequest, HttpServletRequest servletRequest);
    DeleteProductResponse deleteProduct(String productId, HttpServletRequest servletRequest);


    List<Product> viewMyProduct(HttpServletRequest servletRequest);

    List<Product> viewOrder(HttpServletRequest servletRequest);
    Chat chatCustomerCare(String senderId);
    MessageResponse message(SendMessageRequest sendMessageRequest, String chatId);

    List<Vendor> getAllVendor();
}
