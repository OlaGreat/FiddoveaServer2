package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.ProductRequest;

import java.util.List;

public interface ProductService {


    Product addNewProduct(ProductRequest ProductRequest, String vendorId);
    List<Product> findProductByName(String productName);
    Product findById(String id);
    void deleteProduct(String productId);
}
