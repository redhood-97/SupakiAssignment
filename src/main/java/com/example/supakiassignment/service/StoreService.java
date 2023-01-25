package com.example.supakiassignment.service;

import com.example.supakiassignment.dto.PageResponse;
import com.example.supakiassignment.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreService {
    PageResponse<Item> fetchAll(int pageNum, int size);
}
