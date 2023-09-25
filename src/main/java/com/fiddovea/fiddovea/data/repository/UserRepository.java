package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
