package com.example.supakiassignment.service;

import com.example.supakiassignment.entity.Wallet;

public interface WalletService {
    Wallet getWalletBalance(String userId);
}
