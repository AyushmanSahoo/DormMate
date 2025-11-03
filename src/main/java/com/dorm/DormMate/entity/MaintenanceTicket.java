package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("maintenance_tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceTicket {
    @Id
    private String id;

    private String userId;
    private String issueType; // PLUMBING, ELECTRICAL, ETC
    private String description;

    private String status; // QUEUED, IN_PROGRESS, RESOLVED
    private String assignedStaffId;

    private Instant createdAt;
    private Instant updatedAt;
}

