package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {

    Optional<Token> findByownerEmail(String email);

}
