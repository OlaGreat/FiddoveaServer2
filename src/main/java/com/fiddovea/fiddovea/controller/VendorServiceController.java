package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.services.VendorService;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/vendor")

public class VendorServiceController {
    VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorRegistrationResponse> registerVendor(@RequestBody VendorRegistrationRequest registerRequest){
        VendorRegistrationResponse registerResponse = vendorService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<VendorLoginResponse> loginCustomer(@RequestBody LoginRequest loginRequest){
        VendorLoginResponse loginResponse = vendorService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }


    @PutMapping("/updateprofile")
    public ResponseEntity<UpdateVendorResponse>updateUserProfile(@ModelAttribute UpdateVendorRequest updaterVendorRequest, HttpServletRequest requestToken) throws JsonPatchException {
        UpdateVendorResponse response = vendorService.updateProfile(updaterVendorRequest, requestToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductRequest productRequest,HttpServletRequest requestToken){
        ProductResponse addProductResponse = vendorService.addProduct(productRequest, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(addProductResponse);

    }

    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable String productId, HttpServletRequest requestToken){
        DeleteProductResponse response = vendorService.deleteProduct(productId, requestToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/viewproduct")
    public ResponseEntity<List<Product>> viewProductList(HttpServletRequest requestToken){
        List<Product> myProducts = vendorService.viewMyProduct(requestToken);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(myProducts);
    }
    @GetMapping("/myorder")
    public ResponseEntity<List<Product>> viewMyOrder(HttpServletRequest servletRequest){
        List<Product> myOrders = vendorService.viewOrder(servletRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(myOrders);
    }

}
