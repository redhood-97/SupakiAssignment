package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    Page<Order> findByUserId(String userId, PageRequest pageRequest);
}
