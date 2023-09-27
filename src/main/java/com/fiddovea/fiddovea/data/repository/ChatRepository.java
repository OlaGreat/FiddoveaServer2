package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String > {
}
