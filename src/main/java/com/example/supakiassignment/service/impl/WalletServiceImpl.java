package com.example.supakiassignment.service.impl;

import com.example.supakiassignment.entity.Wallet;
import com.example.supakiassignment.repository.WalletRepository;
import com.example.supakiassignment.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    public Wallet getWalletBalance(String userId) {
        try {
            List<Wallet> walletRes = walletRepository.findWalletByUserId(userId);
            return walletRes.get(0);
        }catch(Exception e) {
            log.error("Error encountered while fetching wallet details for user id = {}", userId);
            return null;
        }
    }
}
