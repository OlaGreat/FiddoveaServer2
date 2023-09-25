package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {

}
