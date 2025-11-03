package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("laundry_usage")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaundryUsage {
    @Id
    private String id;

    private String userId;
    private String month; // YYYY-MM
    private int totalLoads;
    private double totalCost;
}
