package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.data.models.Notification;
import com.fiddovea.fiddovea.dto.request.NotificationRequest;
import com.fiddovea.fiddovea.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable String userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addNotification(@PathVariable String userId, @RequestBody NotificationRequest request) {
        notificationService.addNotification(userId, request.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
