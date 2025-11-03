package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.LaundrySlot;
import com.dorm.DormMate.repository.LaundrySlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaundryService {
    @Autowired
    private  LaundrySlotRepository laundryRepo;

    public LaundrySlot bookSlot(LaundrySlot slot) {
        slot.setStatus("BOOKED");
        return laundryRepo.save(slot);
    }

    public List<LaundrySlot> getUserSlots(String userId) {
        return laundryRepo.findByUserId(userId);
    }

    public List<LaundrySlot> getSlotsBetween(Instant start, Instant end) {
        return laundryRepo.findBySlotTimeBetween(start, end);
    }

    public Optional<LaundrySlot> getById(String id) {
        return laundryRepo.findById(id);
    }
}
