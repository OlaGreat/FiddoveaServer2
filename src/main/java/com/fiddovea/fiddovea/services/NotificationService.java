package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Notification;

import java.util.List;

public interface NotificationService {

//    NotificationResponse sendNotification(NotificationRequest notificationRequest);

    List<Notification> getNotificationsByUserId(String userId);

     void addNotification(String userId, String message);
}
