package com.dorm.DormMate.repository;

import com.dorm.DormMate.entity.MaintenanceTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaintenanceTicketRepository extends MongoRepository<MaintenanceTicket, String> {
    List<MaintenanceTicket> findByUserId(String userId);
    List<MaintenanceTicket> findByStatus(String status);
    List<MaintenanceTicket> findByAssignedStaffId(String staffId);
}
