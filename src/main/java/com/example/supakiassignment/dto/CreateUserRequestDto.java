package com.example.supakiassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserRequestDto {
    private String emailAddress;
    private String name;
    private Float initialBalanceAmount;
}
