package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}
