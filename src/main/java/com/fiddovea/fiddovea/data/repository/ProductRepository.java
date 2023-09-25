package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> searchProductByProductName(String ProductName);

}
