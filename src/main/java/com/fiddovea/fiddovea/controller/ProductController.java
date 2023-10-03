package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product")
@CrossOrigin("*")
public class ProductController {
    ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getAllProduct(){
        List<Product> allProduct = productService.getAllProduct();
        return ResponseEntity.status(HttpStatus.OK).body(allProduct);

    }

    @GetMapping("/findproduct/{productId}")
    ResponseEntity<Product> findAProduct(@PathVariable String productId){
        Product product = productService.findById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
