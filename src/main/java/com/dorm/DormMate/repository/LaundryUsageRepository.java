package com.dorm.DormMate.repository;

import com.dorm.DormMate.entity.LaundryUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaundryUsageRepository extends MongoRepository<LaundryUsage, String> {
    Optional<LaundryUsage> findByUserIdAndMonth(String userId, String month);
    List<LaundryUsage> findByUserId(String userId);
}
