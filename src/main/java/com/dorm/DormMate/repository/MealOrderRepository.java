package com.dorm.DormMate.repository;


import com.dorm.DormMate.entity.MealOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealOrderRepository extends MongoRepository<MealOrder, String> {
    List<MealOrder> findByUserId(String userId);
    List<MealOrder> findByUserIdAndOrderDate(String userId, LocalDate orderDate);
}
