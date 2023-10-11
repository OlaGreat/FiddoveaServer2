package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.dto.request.CategoryRequest;
import com.fiddovea.fiddovea.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FIddoveaProductTest {
    @Autowired
    ProductService productService;

    @Test
    void testFIndByCategory(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategory("cake");
        System.out.println(productService.getProductByCategory(categoryRequest));
    }
}
