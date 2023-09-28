package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    void testThatAllProductCanBeFetch(){
       List<Product> allProduct = productService.getAllProduct();
        System.out.println(allProduct);
    }
}
