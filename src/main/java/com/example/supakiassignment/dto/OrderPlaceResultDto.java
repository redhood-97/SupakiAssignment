package com.example.supakiassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlaceResultDto {
    private Boolean success;
    private String msg;
    private String id;
}
