package com.fiddovea.fiddovea.services;


import com.fiddovea.fiddovea.data.models.Chat;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface CustomerService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
//    Optional<Customer> findById(String id);

    GetResponse getCustomerById(String  id);

    Customer findById(String customerId);

    UpdateCustomerResponse updateProfile(UpdateCustomerRequest updateCustomerRequest, String id) throws JsonPatchException;

    WishListResponse addToWishList(WishListRequest wishListRequest);
    List<Product> viewWishList(String customerId);
    List<Product> searchProduct(String productName);

    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);
    String forgetPassword(ForgetPasswordRequest forgetPasswordRequest);
    RemoveProductResponse removeFromCart(RemoveProductRequest removeProductRequest);
    RemoveProductResponse removeFromWishList(RemoveProductRequest request);
//    List<Product> filterByPrice();

    ProductReviewResponse reviewProduct(ProductReviewRequest productReviewRequest);
    Chat chatCustomerCare(String senderId);
    MessageResponse message(SendMessageRequest sendMessageRequest, String chatId);

    TokenVerificationResponse verifyToken(String email, String token);






}


