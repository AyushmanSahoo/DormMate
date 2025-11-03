package com.dorm.DormMate.controller;


import com.dorm.DormMate.entity.MealOrder;
import com.dorm.DormMate.service.MealOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meal-orders")
@RequiredArgsConstructor
public class MealOrderController {
    @Autowired
    private  MealOrderService mealOrderService;

    @PostMapping
    public ResponseEntity<MealOrder> placeOrder(@RequestBody MealOrder order) {
        return ResponseEntity.ok(mealOrderService.placeOrder(order));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealOrder>> getUserOrders(@PathVariable String userId) {
        return ResponseEntity.ok(mealOrderService.getUserOrders(userId));
    }

    @GetMapping("/user/{userId}/date")
    public ResponseEntity<List<MealOrder>> getUserOrdersByDate(
            @PathVariable String userId,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(mealOrderService.getUserOrdersByDate(userId, date));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealOrder> getById(@PathVariable String id) {
        return mealOrderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
