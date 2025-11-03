package com.dorm.DormMate.service;

import com.dorm.DormMate.entity.MealSubscription;
import com.dorm.DormMate.repository.MealSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealSubscriptionService {

    private final MealSubscriptionRepository subscriptionRepository;

    public MealSubscription createSubscription(String userId) {
        if (subscriptionRepository.findByUserId(userId).isPresent()) {
            throw new IllegalStateException("Subscription already exists for this user.");
        }
        MealSubscription subscription = MealSubscription.builder()
                .userId(userId)
                .active(true)
                .skipDates(new ArrayList<>())
                .dailyMealType("VEG") // Default value
                .walletLinked(true)  // Default value
                .build();
        return subscriptionRepository.save(subscription);
    }

    public Optional<MealSubscription> getSubscriptionByUserId(String userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public MealSubscription updateSubscription(String userId, MealSubscription subscriptionDetails) {
        MealSubscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Subscription not found for user: " + userId));
        
        subscription.setActive(subscriptionDetails.isActive());
        subscription.setDailyMealType(subscriptionDetails.getDailyMealType());
        subscription.setWalletLinked(subscriptionDetails.isWalletLinked());
        subscription.setSkipDates(subscriptionDetails.getSkipDates());
        
        return subscriptionRepository.save(subscription);
    }
}
