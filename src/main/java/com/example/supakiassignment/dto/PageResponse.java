package com.example.supakiassignment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@Jacksonized
public class PageResponse<T> implements Serializable {
    List<T> items;
    long total;
    long totalPages;
    long currentPage;
}
