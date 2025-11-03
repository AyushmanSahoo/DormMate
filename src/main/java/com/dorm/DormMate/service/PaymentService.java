package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.Payment;
import com.dorm.DormMate.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private  PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        payment.setTimestamp(Instant.now());
        return paymentRepository.save(payment);
    }

    public List<Payment> getByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
}
