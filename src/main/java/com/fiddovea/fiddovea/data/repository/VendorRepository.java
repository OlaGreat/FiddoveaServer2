package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VendorRepository extends MongoRepository<Vendor, String> {
    Optional<Vendor> readByEmail(String email);
}
