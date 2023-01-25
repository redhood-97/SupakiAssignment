package com.example.supakiassignment.controller;

import com.example.supakiassignment.dto.ResponseDto;
import com.example.supakiassignment.entity.Wallet;
import com.example.supakiassignment.repository.WalletRepository;
import com.example.supakiassignment.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getWallet(@RequestParam(name = "userId") String userId) {
        try {
            Wallet w = walletService.getWalletBalance(userId);
            return new ResponseEntity<>(ResponseDto.builder().data(w).build(), HttpStatus.OK);
        }catch(Exception e) {
            log.error("Error encountered while fetching wallet details ", e);
            return new ResponseEntity<>(ResponseDto.builder().data("Some went wrong. Can't fetch wallet details").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
