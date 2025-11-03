package com.dorm.DormMate.repository;

import com.dorm.DormMate.entity.MealSubscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealSubscriptionRepository extends MongoRepository<MealSubscription, String> {
    Optional<MealSubscription> findByUserId(String userId);
}
