package com.dorm.DormMate.controller;


import com.dorm.DormMate.entity.Payment;
import com.dorm.DormMate.service.PaymentService;
import com.dorm.DormMate.service.RazorpayService;
import com.razorpay.Order;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RazorpayService razorpayService;
    private final PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createRazorpayOrder(@RequestParam int amount, @RequestParam(defaultValue = "INR") String currency) {
        try {
            Order order = razorpayService.createOrder(amount, currency);
            JSONObject response = new JSONObject();
            response.put("id", Optional.ofNullable(order.get("id")));
            response.put("amount", Optional.ofNullable(order.get("amount")));
            response.put("currency", Optional.ofNullable(order.get("currency")));
            response.put("receipt", Optional.ofNullable(order.get("receipt")));
            return ResponseEntity.ok(response.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating Razorpay order: " + e.getMessage());
        }
    }

    @PostMapping("/record")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.save(payment));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getUserPayments(@PathVariable String userId) {
        return ResponseEntity.ok(paymentService.getByUserId(userId));
    }
}
