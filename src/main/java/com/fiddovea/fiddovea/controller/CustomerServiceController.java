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
@CrossOrigin("*")
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


    @PutMapping("/update/{id}")
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

    @PostMapping("/addtocart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody CartRequest cartRequest, HttpServletRequest requestToken) {
        AddToCartResponse addToCartResponse = customerService.addToCart(cartRequest.getProductId(), requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(addToCartResponse);
    }
    @PostMapping("/addtowishlist")
    public ResponseEntity<WishListResponse> addToWishList(@RequestBody WishListRequest wishListRequest, HttpServletRequest requestToken){
        WishListResponse wishListResponse = customerService.addToWishList(wishListRequest.getProductId(), requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(wishListResponse);
    }

    @PostMapping("/removefromcart")
    public ResponseEntity<RemoveProductResponse> removeProductFromCart(@RequestBody CartRequest cartRequest, HttpServletRequest requestToken){
        RemoveProductResponse response = customerService.removeFromCart(cartRequest.getProductId(), requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/removefromwishlist")
    public ResponseEntity<RemoveProductResponse> removeProductFromWishList(@RequestBody WishListRequest wishListRequest, HttpServletRequest requestToken){
        RemoveProductResponse response = customerService.removeFromWishList(wishListRequest.getProductId(), requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String query) {
        List<Product> productSearch = customerService.searchProduct(query);
        return ResponseEntity.status(HttpStatus.OK).body(productSearch);
    }

    @PostMapping("/verifytoken")
    public ResponseEntity<TokenVerificationResponse> verifyToken(@RequestBody VerifyTokenRequest verifyTokenRequest, String token){
        TokenVerificationResponse response = customerService.verifyToken(verifyTokenRequest.getEmail(),token);
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
    public ResponseEntity<ConfirmOrderResponse> order (@RequestBody OrderRequest orderRequest, HttpServletRequest requestToken){
        ConfirmOrderResponse orderResponse = customerService.order(orderRequest, requestToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PostMapping("/review")
    public ResponseEntity<ProductReviewResponse> reviewProduct(@RequestBody ProductReviewRequest reviewRequest, HttpServletRequest servletRequest){
        ProductReviewResponse response = customerService.reviewProduct(reviewRequest, reviewRequest.getProductId(), servletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}


