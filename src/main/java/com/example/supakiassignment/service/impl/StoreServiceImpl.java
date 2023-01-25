package com.example.supakiassignment.service.impl;

import com.example.supakiassignment.dto.PageResponse;
import com.example.supakiassignment.entity.Item;
import com.example.supakiassignment.repository.StoreRepository;
import com.example.supakiassignment.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public PageResponse<Item> fetchAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page page = storeRepository.findAll(pageable);
        log.info("Total elements in the current page = {}", page.getTotalElements());
        PageResponse<Item> res = PageResponse.<Item>builder()
                .items(page.getContent())
                .currentPage(pageNum)
                .total(page.getContent().size())
                .totalPages(page.getTotalPages())
                .build();

        return res;
    }
}
