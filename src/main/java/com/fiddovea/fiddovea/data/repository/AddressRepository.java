package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
