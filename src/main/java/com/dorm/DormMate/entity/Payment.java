package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    private String id;

    private String userId;
    private double amount;
    private String type; // RENT, WALLET_TOPUP, LAUNDRY, MESS
    private String status; // INITIATED, SUCCESS, FAILED

    private String paymentGatewayRef;
    private Instant timestamp;
}

