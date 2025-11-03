package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("meal_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealOrder {
    @Id
    private String id;

    private LocalDate orderDate;
    private String userId;
    private LocalDate mealDate;
    private String mealType; // BREAKFAST, LUNCH, DINNER
    private double price;
    private String status; // ORDERED, CANCELLED, DELIVERED
}
