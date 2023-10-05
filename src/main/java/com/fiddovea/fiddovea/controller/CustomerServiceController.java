package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.services.CustomerService;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
@Slf4j
//@CrossOrigin("*")

public class CustomerServiceController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = customerService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = customerService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse>updateUserProfile(@ModelAttribute UpdateCustomerRequest updaterCustomerRequest, @PathVariable String id) throws JsonPatchException {
        UpdateCustomerResponse response = customerService.updateProfile(updaterCustomerRequest, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<Product>> viewWishList(HttpServletRequest requestToken) {
        List<Product> wishList = customerService.viewWishList(requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }

    @PostMapping("/forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest request) {
        String message = customerService.forgetPassword(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @PostMapping("/addtocart/{productId}")
    public ResponseEntity<AddToCartResponse> addToCart(@PathVariable String productId, HttpServletRequest requestToken) {
        AddToCartResponse addToCartResponse = customerService.addToCart(productId, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(addToCartResponse);
    }
    @PostMapping("/addtowishlist/{productId}")
    public ResponseEntity<WishListResponse> addToWishList(@PathVariable String productId, HttpServletRequest requestToken){
        WishListResponse wishListResponse = customerService.addToWishList(productId, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(wishListResponse);
    }

    @PostMapping("/removefromcart/{productId}")
    public ResponseEntity<RemoveProductResponse> removeProductFromCart(@PathVariable String productId, HttpServletRequest requestToken){
        RemoveProductResponse response = customerService.removeFromCart(productId, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/removefromwishlist/{productId}")
    public ResponseEntity<RemoveProductResponse> removeProductFromWishList(@PathVariable String productId, HttpServletRequest requestToken){
        RemoveProductResponse response = customerService.removeFromWishList(productId, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String productName){
        List<Product> productSearch = customerService.searchProduct(productName);
        return ResponseEntity.status(HttpStatus.OK).body(productSearch);
    }

    @PostMapping("/verifytoken/{email}")
    public ResponseEntity<TokenVerificationResponse> verifyToken(@PathVariable String email, String token){
        TokenVerificationResponse response = customerService.verifyToken(email,token);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/viewcart")
    public ResponseEntity<List<Product>> viewCart(HttpServletRequest requestToken){
        List<Product> cart = customerService.viewCart(requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @GetMapping("/view-orderHistory")
    //TODO TEST WITH POSTMAN
    public ResponseEntity<List<Order>> viewOrderHistory(HttpServletRequest requestToken){
        List<Order> orderHistory = customerService.viewOrderHistory(requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(orderHistory);
    }

    @PostMapping("/order")
    public ResponseEntity<ConfirmOrderResponse> order (OrderRequest orderRequest, HttpServletRequest requestToken){
        ConfirmOrderResponse orderResponse = customerService.order(orderRequest, requestToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/review/{productId}")
    public ResponseEntity<ProductReviewResponse> reviewProduct(ProductReviewRequest reviewRequest, @PathVariable String productId, HttpServletRequest servletRequest){
        ProductReviewResponse response = customerService.reviewProduct(reviewRequest, productId, servletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }



}


