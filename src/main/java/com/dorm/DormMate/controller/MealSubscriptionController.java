package com.dorm.DormMate.controller;

import com.dorm.DormMate.entity.MealSubscription;
import com.dorm.DormMate.service.MealSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class MealSubscriptionController {

    private final MealSubscriptionService subscriptionService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<MealSubscription> createSubscription(@PathVariable String userId) {
        return ResponseEntity.ok(subscriptionService.createSubscription(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<MealSubscription> getSubscription(@PathVariable String userId) {
        return subscriptionService.getSubscriptionByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<MealSubscription> updateSubscription(@PathVariable String userId, @RequestBody MealSubscription subscriptionDetails) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(userId, subscriptionDetails));
    }
}
