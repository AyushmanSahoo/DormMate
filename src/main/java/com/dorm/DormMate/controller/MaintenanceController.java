package com.dorm.DormMate.controller;

import com.dorm.DormMate.entity.MaintenanceTicket;
import com.dorm.DormMate.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceTicket> createTicket(@RequestBody MaintenanceTicket ticket) {
        return ResponseEntity.ok(maintenanceService.createTicket(ticket));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MaintenanceTicket>> getUserTickets(@PathVariable String userId) {
        return ResponseEntity.ok(maintenanceService.getTicketsByUser(userId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<MaintenanceTicket>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(maintenanceService.getTicketsByStatus(status));
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<MaintenanceTicket>> getByStaff(@PathVariable String staffId) {
        return ResponseEntity.ok(maintenanceService.getTicketsByStaff(staffId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceTicket> getById(@PathVariable String id) {
        return maintenanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceTicket> updateTicket(@PathVariable String id, @RequestBody MaintenanceTicket ticket) {
        ticket.setId(id);
        return ResponseEntity.ok(maintenanceService.updateTicket(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id) {
        maintenanceService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
