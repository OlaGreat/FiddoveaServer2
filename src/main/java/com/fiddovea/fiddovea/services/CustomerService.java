package com.fiddovea.fiddovea.services;


import com.fiddovea.fiddovea.data.models.Chat;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CustomerService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    void logOut(HttpServletRequest request);
//    Optional<Customer> findById(String id);

    GetResponse getCustomerById(String  id);

    Customer findById(String customerId);

    UpdateCustomerResponse updateProfile(UpdateCustomerRequest updateCustomerRequest, String id) throws JsonPatchException;

    WishListResponse addToWishList(String productId, HttpServletRequest servletRequest);
    List<Product> viewWishList(HttpServletRequest request);
    List<Product> searchProduct(String productName);

    AddToCartResponse addToCart(String productId, HttpServletRequest request);
    String forgetPassword(ForgetPasswordRequest forgetPasswordRequest);
    RemoveProductResponse removeFromCart(String productId, HttpServletRequest servletRequest);
    RemoveProductResponse removeFromWishList(String productId, HttpServletRequest servletRequest);
//    List<Product> filterByPrice();

    ProductReviewResponse reviewProduct(ProductReviewRequest productReviewRequest, String productId, HttpServletRequest requestToken);
    Chat chatCustomerCare(HttpServletRequest servletRequest);
    MessageResponse message(SendMessageRequest sendMessageRequest, String chatId);

    TokenVerificationResponse verifyToken(String email, String token);

    List<Product> viewCart(HttpServletRequest servletRequest);

    ConfirmOrderResponse order(OrderRequest orderRequest, HttpServletRequest servletRequest);

    List<Order> viewOrderHistory(HttpServletRequest servletRequest);

}


