package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.dto.response.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest notificationRequest);
}
