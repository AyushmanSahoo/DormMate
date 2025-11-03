package com.dorm.DormMate.entity;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class LaundryHistory {
    private LocalDateTime date;
    private int itemsCount;

    // Getters & Setters
}

