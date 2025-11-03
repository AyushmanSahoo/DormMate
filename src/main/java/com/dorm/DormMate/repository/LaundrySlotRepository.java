package com.dorm.DormMate.repository;




import com.dorm.DormMate.entity.LaundrySlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface LaundrySlotRepository extends MongoRepository<LaundrySlot, String> {
    List<LaundrySlot> findByUserId(String userId);
    List<LaundrySlot> findBySlotTimeBetween(Instant start, Instant end);
}

