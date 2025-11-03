package com.dorm.DormMate.entity;




import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("hostels")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hostel {
    @Id
    private String id;

    private String name;
    private String location;
    private int capacity;
    private String type; // BOYS, GIRLS, MIXED
    private List<String> rooms; // optional ,rooms available
}

