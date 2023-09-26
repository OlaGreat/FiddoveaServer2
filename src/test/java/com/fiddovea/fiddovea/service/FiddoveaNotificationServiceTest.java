package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.dto.response.NotificationResponse;
import com.fiddovea.fiddovea.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FiddoveaNotificationServiceTest {


    @Autowired
    NotificationService notificationService;



    @Test
    public void testThatWeCanSendANotification() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setRecipients_id("65125697985f4b19b406d7b2");
        notificationRequest.setSender_id("650fc32c5b254c300f7820dc");
        notificationRequest.setSubject("notification sent");

        NotificationResponse response = notificationService.sendNotification(notificationRequest);

        assertThat(response).isNotNull();
        assertNotNull(response.getMessage());
    }

}