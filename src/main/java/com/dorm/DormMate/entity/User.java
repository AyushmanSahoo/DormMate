package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("users")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String passwordHash;
    private String role; // STUDENT, WARDEN, STAFF, ADMIN
    private String phoneNumber;

    private String hostelId;

    private Instant createdAt;
    private Instant updatedAt;
}

