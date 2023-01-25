package com.example.supakiassignment.service;

import com.example.supakiassignment.dto.OrderPlaceResultDto;
import com.example.supakiassignment.entity.Order;

import java.util.List;

public interface OrderService {
    public OrderPlaceResultDto placeOrder(String userId, String itemId);
    public List<Order> findOrdersOfAUser(String userId);
}
