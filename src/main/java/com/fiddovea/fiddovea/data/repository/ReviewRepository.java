package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
