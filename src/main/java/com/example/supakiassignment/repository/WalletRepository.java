package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet save(Wallet wallet);

    @Query(value = "select * from public.wallet_info where user_id = :userId", nativeQuery = true)
    List<Wallet> findWalletByUserId(String userId);
}
