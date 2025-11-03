package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("laundry_slots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaundrySlot {
    @Id
    private String id;

    private String userId;
    private Instant slotTime;
    private String status; // BOOKED, PICKED_UP, DELIVERED

    private Instant pickupTime;
    private Instant deliveryTime;

    private String month; // YYYY-MM
}

