package com.dorm.DormMate.repository;

import com.dorm.DormMate.entity.Hostel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends MongoRepository<Hostel, String> {
}
