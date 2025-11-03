package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.MaintenanceTicket;
import com.dorm.DormMate.repository.MaintenanceTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    @Autowired
    private  MaintenanceTicketRepository ticketRepo;

    public MaintenanceTicket createTicket(MaintenanceTicket ticket) {
        ticket.setStatus("QUEUED");
        return ticketRepo.save(ticket);
    }

    public List<MaintenanceTicket> getTicketsByUser(String userId) {
        return ticketRepo.findByUserId(userId);
    }

    public List<MaintenanceTicket> getTicketsByStatus(String status) {
        return ticketRepo.findByStatus(status);
    }

    public List<MaintenanceTicket> getTicketsByStaff(String staffId) {
        return ticketRepo.findByAssignedStaffId(staffId);
    }

    public Optional<MaintenanceTicket> getById(String id) {
        return ticketRepo.findById(id);
    }

    public MaintenanceTicket updateTicket(MaintenanceTicket ticket) {
        return ticketRepo.save(ticket);
    }

    public void deleteTicket(String id) {
        ticketRepo.deleteById(id);
    }
}
