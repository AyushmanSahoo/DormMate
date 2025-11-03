package com.dorm.DormMate.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RazorpayService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public Order createOrder(int amountInRupees, String currency) throws Exception {
        RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInRupees * 100); // amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", UUID.randomUUID().toString());
        orderRequest.put("payment_capture", true); // auto capture

        return client.orders.create(orderRequest);
    }
}
