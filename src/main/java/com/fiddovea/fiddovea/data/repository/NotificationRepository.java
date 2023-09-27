package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByUserId(String userId);
}
