package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.Wallet;
import com.dorm.DormMate.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class WalletService {
    @Autowired
    private  WalletRepository walletRepository;

    public Wallet getWallet(String userId) {
        return walletRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Wallet newWallet = Wallet.builder()
                            .userId(userId)
                            .balance(0.0)
                            .transactions(new ArrayList<>())
                            .build();
                    return walletRepository.save(newWallet);
                });
    }

    public Wallet topUp(String userId, double amount, String reason) {
        Wallet wallet = getWallet(userId);

        wallet.setBalance(wallet.getBalance() + amount);

        wallet.getTransactions().add(
                Wallet.Transaction.builder()
                        .amount(amount)
                        .reason(reason)
                        .type("CREDIT")
                        .timestamp(Instant.now())
                        .build()
        );

        return walletRepository.save(wallet);
    }

    public Wallet deduct(String userId, double amount, String reason) {
        Wallet wallet = getWallet(userId);

        if (wallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - amount);

        wallet.getTransactions().add(
                Wallet.Transaction.builder()
                        .amount(amount)
                        .reason(reason)
                        .type("DEBIT")
                        .timestamp(Instant.now())
                        .build()
        );

        return walletRepository.save(wallet);
    }
}
