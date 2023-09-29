package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.services.CustomerService;
import com.fiddovea.fiddovea.dto.request.RemoveProductRequest;
import com.github.fge.jsonpatch.JsonPatchException;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateCustomerResponse>updateUserProfile(@ModelAttribute UpdateCustomerRequest updaterCustomerRequest, @PathVariable String id) throws JsonPatchException {
        UpdateCustomerResponse response = customerService.updateProfile(updaterCustomerRequest, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wishlist/{customerId}")
    public ResponseEntity<List<Product>> viewWishList(@PathVariable String customerId) {
        List<Product> wishList = customerService.viewWishList(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }

    @PostMapping("/forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest request) {
        String message = customerService.forgetPassword(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @PostMapping("/addtocart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        AddToCartResponse addToCartResponse = customerService.addToCart(addToCartRequest);
        return ResponseEntity.status(HttpStatus.OK).body(addToCartResponse);
    }
    @PostMapping("/addtowishlist")
    public ResponseEntity<WishListResponse> addToWishList(@RequestBody WishListRequest wishListRequest){
        WishListResponse wishListResponse = customerService.addToWishList(wishListRequest);
        return ResponseEntity.status(HttpStatus.OK).body(wishListResponse);
    }

    @PostMapping("/removefromcart")
    public ResponseEntity<RemoveProductResponse> removeProductFromCart(@RequestBody RemoveProductRequest removeProductRequest){
        RemoveProductResponse response = customerService.removeFromCart(removeProductRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("removefromwishlist")
    public ResponseEntity<RemoveProductResponse> removeProductFromWishList(@RequestBody RemoveProductRequest removeProductRequest){
        RemoveProductResponse response = customerService.removeFromWishList(removeProductRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String productName){
        List<Product> productSearch = customerService.searchProduct(productName);
        return ResponseEntity.status(HttpStatus.OK).body(productSearch);
    }


}


