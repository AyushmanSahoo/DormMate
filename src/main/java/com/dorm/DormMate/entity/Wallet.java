package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("wallets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    private String id;

    private String userId;
    private double balance;
    private List<Transaction> transactions;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Transaction {
        private double amount;
        private String reason;
        private String type; // CREDIT, DEBIT
        private Instant timestamp;
    }
}

