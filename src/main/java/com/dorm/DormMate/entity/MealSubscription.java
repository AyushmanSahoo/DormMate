package com.dorm.DormMate.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("meal_subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealSubscription {
    @Id
    private String id;

    private String userId;
    private boolean active;
    private List<String> skipDates; // ISO date strings
    private String dailyMealType; // VEG, NON_VEG
    private boolean walletLinked;
}


