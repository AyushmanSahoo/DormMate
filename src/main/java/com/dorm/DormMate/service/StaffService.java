package com.dorm.DormMate.service;

import com.dorm.DormMate.entity.MaintenanceTicket;
import com.dorm.DormMate.entity.Staff;
import com.dorm.DormMate.repository.MaintenanceTicketRepository;
import com.dorm.DormMate.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final MaintenanceTicketRepository maintenanceTicketRepository;

    public Staff createStaff(Staff staff) {
        if (staff.getAssignedTickets() == null) {
            staff.setAssignedTickets(new ArrayList<>());
        }
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }
    
    public Staff assignTicket(String staffId, String ticketId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        MaintenanceTicket ticket = maintenanceTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        ticket.setAssignedStaffId(staffId);
        ticket.setStatus("IN_PROGRESS");
        maintenanceTicketRepository.save(ticket);
        
        staff.getAssignedTickets().add(ticketId);
        return staffRepository.save(staff);
    }

    public Staff resolveTicket(String staffId, String ticketId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        MaintenanceTicket ticket = maintenanceTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        ticket.setStatus("RESOLVED");
        maintenanceTicketRepository.save(ticket);
        
        staff.getAssignedTickets().remove(ticketId);
        staff.setTotalResolved(staff.getTotalResolved() + 1);
        return staffRepository.save(staff);
    }

    public void deleteStaff(String id) {
        staffRepository.deleteById(id);
    }
}
