package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.data.repository.NotificationRepository;
import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.dto.response.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fiddovea.fiddovea.dto.response.ResponseMessage.NOTIFICATION_SENT_SUCCESSFULLY;

@Service
@AllArgsConstructor
public class FiddoveaNotificationService implements NotificationService{

    private final CustomerService userService;
    private final NotificationRepository notificationRepository;


    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        String  sender_id = notificationRequest.getSender_id();
        String receivers_id = notificationRequest.getRecipients_id();
        String header = notificationRequest.getSubject();

        Notification notification = new Notification();
        notification.setSender(sender_id);

        Customer foundUser = userService.findById(receivers_id);
        notification.setCustomer(foundUser);

        notification.setContent(header);

        notificationRepository.save(notification);

        return NotificationResponse.builder()
                .message(NOTIFICATION_SENT_SUCCESSFULLY.name())
                .build();
    }
}