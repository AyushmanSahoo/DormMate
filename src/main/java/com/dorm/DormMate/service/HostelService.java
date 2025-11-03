package com.dorm.DormMate.service;

import com.dorm.DormMate.entity.Hostel;
import com.dorm.DormMate.repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    public Hostel createHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }

    public Optional<Hostel> getHostelById(String id) {
        return hostelRepository.findById(id);
    }

    public Hostel updateHostel(String id, Hostel hostelDetails) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));
        
        hostel.setName(hostelDetails.getName());
        hostel.setLocation(hostelDetails.getLocation());
        hostel.setCapacity(hostelDetails.getCapacity());
        hostel.setType(hostelDetails.getType());
        hostel.setRooms(hostelDetails.getRooms());
        
        return hostelRepository.save(hostel);
    }

    public void deleteHostel(String id) {
        hostelRepository.deleteById(id);
    }
}
