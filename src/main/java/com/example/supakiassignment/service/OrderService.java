package com.example.supakiassignment.service;

import com.example.supakiassignment.dto.OrderPlaceResultDto;
import com.example.supakiassignment.dto.PageResponse;
import com.example.supakiassignment.entity.Order;

import java.util.List;

public interface OrderService {
    OrderPlaceResultDto placeOrder(String userId, String itemId);
    PageResponse<Order> findAllOrders(String userId, int pageNum, int pageSize);
}
