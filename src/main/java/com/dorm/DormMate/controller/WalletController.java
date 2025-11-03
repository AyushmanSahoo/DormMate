package com.dorm.DormMate.controller;


import com.dorm.DormMate.entity.Wallet;
import com.dorm.DormMate.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String userId) {
        return ResponseEntity.ok(walletService.getWallet(userId));
    }

    @PostMapping("/topup")
    public ResponseEntity<Wallet> topUpWallet(
            @RequestParam String userId,
            @RequestParam double amount,
            @RequestParam(defaultValue = "Wallet Top-Up") String reason
    ) {
        return ResponseEntity.ok(walletService.topUp(userId, amount, reason));
    }

    @PostMapping("/deduct")
    public ResponseEntity<Wallet> deductFromWallet(
            @RequestParam String userId,
            @RequestParam double amount,
            @RequestParam(defaultValue = "Payment for service") String reason
    ) {
        return ResponseEntity.ok(walletService.deduct(userId, amount, reason));
    }
}
