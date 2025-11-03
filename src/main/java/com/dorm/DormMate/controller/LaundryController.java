package com.dorm.DormMate.controller;


import com.dorm.DormMate.entity.LaundrySlot;
import com.dorm.DormMate.service.LaundryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/laundry")
@RequiredArgsConstructor
public class LaundryController {
    private final LaundryService laundryService;

    @PostMapping("/book")
    public ResponseEntity<LaundrySlot> bookSlot(@RequestBody LaundrySlot slot) {
        return ResponseEntity.ok(laundryService.bookSlot(slot));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LaundrySlot>> getUserSlots(@PathVariable String userId) {
        return ResponseEntity.ok(laundryService.getUserSlots(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaundrySlot> getById(@PathVariable String id) {
        return laundryService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/range")
    public ResponseEntity<List<LaundrySlot>> getByRange(
            @RequestParam Instant start,
            @RequestParam Instant end) {
        return ResponseEntity.ok(laundryService.getSlotsBetween(start, end));
    }
}
