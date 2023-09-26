package com.fiddovea.fiddovea.data.repository;

import com.fiddovea.fiddovea.data.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
