package com.dorm.DormMate.controller;


import com.dorm.DormMate.entity.Notification;
import com.dorm.DormMate.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String message,
            @RequestParam String channel
    ) {
        return ResponseEntity.ok(notificationService.sendNotification(userId, title, message, channel));
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Notification> broadcastNotification(
            @RequestParam String title,
            @RequestParam String message,
            @RequestParam String channel
    ) {
        return ResponseEntity.ok(notificationService.broadcastNotification(title, message, channel));
    }
}
