package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Chat;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;

import java.util.List;

public interface CustomerService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
//    Optional<Customer> findById(String id);
    WishListResponse addToWishList(WishListRequest wishListRequest);
    List<Product> viewWishList(String customerId);
    List<Product> searchProduct(String productName);

    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);
    String forgetPassword(ForgetPasswordRequest forgetPasswordRequest);
    RemoveProductResponse removeFromCart(RemoveProductRequest removeProductRequest);
    RemoveProductResponse removeFromWishList(RemoveProductRequest request);
//    List<Product> filterByPrice();

    ProductReviewResponse reviewProduct(ProductReviewRequest productReviewRequest, String customerId);
    Chat chatCustomerCare(String senderId);
    MessageResponse message(SendMessageRequest sendMessageRequest, String chatId);



}


