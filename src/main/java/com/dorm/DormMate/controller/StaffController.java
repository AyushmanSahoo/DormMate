package com.dorm.DormMate.controller;

import com.dorm.DormMate.entity.Staff;
import com.dorm.DormMate.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN', 'WARDEN')")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.createStaff(staff));
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable String id) {
        return staffService.getStaffById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{staffId}/assign/{ticketId}")
    public ResponseEntity<Staff> assignTicket(@PathVariable String staffId, @PathVariable String ticketId) {
        return ResponseEntity.ok(staffService.assignTicket(staffId, ticketId));
    }
    
    @PostMapping("/{staffId}/resolve/{ticketId}")
    public ResponseEntity<Staff> resolveTicket(@PathVariable String staffId, @PathVariable String ticketId) {
        return ResponseEntity.ok(staffService.resolveTicket(staffId, ticketId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable String id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok().build();
    }
}
