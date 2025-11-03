package com.dorm.DormMate.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("staff")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    private String id;

    private String name;
    private List<String> assignedTickets; // ticket IDs
    private int totalResolved;
}

