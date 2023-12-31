package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.data.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> searchProductByProductName(String ProductName);

    List<Product> findByProductType(ProductType productType);
}
