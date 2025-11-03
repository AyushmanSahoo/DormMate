package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.MealOrder;
import com.dorm.DormMate.repository.MealOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealOrderService {
    @Autowired
    private  MealOrderRepository mealOrderRepository;

    public MealOrder placeOrder(MealOrder order) {
        return mealOrderRepository.save(order);
    }

    public List<MealOrder> getUserOrders(String userId) {
        return mealOrderRepository.findByUserId(userId);
    }

    public List<MealOrder> getUserOrdersByDate(String userId, LocalDate date) {
        return mealOrderRepository.findByUserIdAndOrderDate(userId, date);
    }

    public Optional<MealOrder> getById(String id) {
        return mealOrderRepository.findById(id);
    }
}
