package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.DeleteProductResponse;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.ProductResponse;
import com.fiddovea.fiddovea.dto.response.VendorRegistrationResponse;
import com.fiddovea.fiddovea.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/vendor")
@CrossOrigin("*")
public class VendorServiceController {
    VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorRegistrationResponse> registerCustomer(@RequestBody VendorRegistrationRequest registerRequest){
        VendorRegistrationResponse registerResponse = vendorService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = vendorService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

    @PostMapping("/addproduct/{vendorId}")
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductRequest productRequest, @PathVariable String vendorId){
        ProductResponse addProductResponse = vendorService.addProduct(productRequest, vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(addProductResponse);

    }

    @DeleteMapping("/deleteproduct/{vendorId}{productId}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable String vendorId, @PathVariable String productId){
        DeleteProductResponse response = vendorService.deleteProduct(vendorId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/viewproduct/{vendorId}")
    public ResponseEntity<List<Product>> viewProductList(@PathVariable String vendorId){
        List<Product> myProducts = vendorService.viewMyProduct(vendorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(myProducts);
    }
    @GetMapping("/myorder/{vendorId}")
    public ResponseEntity<List<Product>> viewMyOrder(@PathVariable String vendorId){
        List<Product> myOrders = vendorService.viewOrder(vendorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(myOrders);
    }


}
