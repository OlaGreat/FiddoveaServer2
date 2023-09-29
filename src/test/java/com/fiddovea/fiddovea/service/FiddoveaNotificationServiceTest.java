package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.data.repository.CustomerRepository;
import com.fiddovea.fiddovea.data.repository.NotificationRepository;
import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.dto.response.NotificationResponse;
import com.fiddovea.fiddovea.services.FiddoveaNotificationService;
import com.fiddovea.fiddovea.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FiddoveaNotificationServiceTest {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private FiddoveaNotificationService notificationService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testGetNotificationsByUserId() {
        Customer customer = new Customer();
        customer.setFirstName("Mood");
        customer.setLastName("Love");
        customer.setEmail("anotherlove@gmail.com");
        customerRepository.save(customer);

//        Notification notification1 = new Notification();
//        notification1.setMessage("Notification 1");
//        notification1.setTimestamp(LocalDateTime.now());
//        notification1.setUserId(customer.getId());
//
//        Notification notification2 = new Notification();
//        notification2.setMessage("Notification 2");
//        notification2.setTimestamp(LocalDateTime.now());
//        notification2.setUserId(customer.getId());
//
//        notificationRepository.save(notification1);
//        notificationRepository.save(notification2);
//
//        // Act
//        List<Notification> notifications = notificationService.getNotificationsByUserId(customer.getId());
//
//        // Assert
//        assertEquals(2, notifications.size());
//        assertEquals("Notification 1", notifications.get(0).getMessage());
//        assertEquals("Notification 2", notifications.get(1).getMessage());
    }

}