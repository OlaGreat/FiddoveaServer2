package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Message;
import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.data.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FiddoveaNotificationService implements NotificationService{

    private final NotificationRepository notificationRepository;


    @Override
    public void addNotification(String userId, List<String> messages) {
        Notification notification = new Notification();
        notification.setUserId(userId);
//        notification.setTimestamp(LocalDateTime.now());

        List<Message> messageList = messages.stream()
                .map(messageContent -> {
                    Message message = new Message();
                    message.setContent(messageContent);
//                    message.setNotification(notification);
                    return message;
                })
                .collect(Collectors.toList());

        notification.setMessages(messageList);

        notificationRepository.save(notification);
    }



    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);

        if (notifications.isEmpty()) {
            // If no notifications are found, create a dummy notification
            Notification noMessageNotification = new Notification();
            noMessageNotification.setUserId(userId);

            // Create a list with a single message in the dummy notification
            Message noMessage = new Message();
            noMessage.setContent("There are no messages.");
            noMessageNotification.setMessages(Collections.singletonList(noMessage));

            // Create a list with the dummy notification
            List<Notification> result = new ArrayList<>();
            result.add(noMessageNotification);

            return result;
        }

        return notifications;
    }



//    @Override
//    public List<Notification> getNotificationsByUserId(String userId) {
//        return notificationRepository.findByUserId(userId);
//    }

//    @Override
//    public List<Notification> getNotificationsByUserId(String userId) {
//        List<Notification> notifications = notificationRepository.findByUserId(userId);
//
//        // Fetch the associated messages for each notification
//        for (Notification notification : notifications) {
//            notification.getMessages().size(); // This will trigger lazy loading
//        }
//
//        return notifications;
//    }



}