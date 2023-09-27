package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.data.repository.NotificationRepository;
import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.dto.response.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NOTIFICATION_SENT_SUCCESSFULLY;

@Service
@AllArgsConstructor
public class FiddoveaNotificationService implements NotificationService{

    private final NotificationRepository notificationRepository;


    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public void addNotification(String userId, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setUserId(userId);
        notificationRepository.save(notification);
    }
}