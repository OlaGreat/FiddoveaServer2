package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.dto.request.UpdateVendorRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.*;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface VendorService {
    VendorRegistrationResponse register(VendorRegistrationRequest request);
    LoginResponse login(LoginRequest loginRequest);

    GetResponse getVendorById(String id);
    UpdateVendorResponse updateProfile(UpdateVendorRequest updateVendorRequest, String id) throws JsonPatchException;
    ProductResponse addProduct(ProductRequest productRequest, String vendorId);
    DeleteProductResponse deleteProduct(String vendorId, String productId);


    List<Product> viewMyProduct(String vendorId);

    List<Product> viewOrder(String vendorId);
}
