package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Admin;
import com.fiddovea.fiddovea.data.models.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> readByEmail(String email);
}
