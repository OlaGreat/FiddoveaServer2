package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Order;
import com.fiddovea.fiddovea.dto.request.ViewOrderRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository <Order, String> {

}
