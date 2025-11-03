package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;

    private String userId;
    private String channel; // SMS, EMAIL, IN_APP
    private String title;
    private String message;

    private boolean read;
    private Instant sentAt;
}

