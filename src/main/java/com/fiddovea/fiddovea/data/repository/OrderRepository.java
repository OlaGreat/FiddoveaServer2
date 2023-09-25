package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository <Order, String> {
}
