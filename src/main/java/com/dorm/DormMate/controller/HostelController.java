package com.dorm.DormMate.controller;

import com.dorm.DormMate.entity.Hostel;
import com.dorm.DormMate.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hostel> createHostel(@RequestBody Hostel hostel) {
        return ResponseEntity.ok(hostelService.createHostel(hostel));
    }

    @GetMapping
    public ResponseEntity<List<Hostel>> getAllHostels() {
        return ResponseEntity.ok(hostelService.getAllHostels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable String id) {
        return hostelService.getHostelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hostel> updateHostel(@PathVariable String id, @RequestBody Hostel hostelDetails) {
        return ResponseEntity.ok(hostelService.updateHostel(id, hostelDetails));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHostel(@PathVariable String id) {
        hostelService.deleteHostel(id);
        return ResponseEntity.ok().build();
    }
}
