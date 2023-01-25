package com.example.supakiassignment.controller;

import com.example.supakiassignment.dto.OrderPlaceRequestDto;
import com.example.supakiassignment.dto.OrderPlaceResultDto;
import com.example.supakiassignment.dto.ResponseDto;
import com.example.supakiassignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> placeAnOrder(@RequestBody OrderPlaceRequestDto orderPlaceRequestDto) {
        try {
            OrderPlaceResultDto res = orderService.placeOrder(orderPlaceRequestDto.getUserId(), orderPlaceRequestDto.getItemId());
            return new ResponseEntity<>(ResponseDto.builder().data(res.getMsg()).build(), HttpStatus.OK);
        }catch(Exception e) {
            log.error("Error encountered while puchasing item ", e);
            return new ResponseEntity<>(ResponseDto.builder()
                            .data("Error while ordering").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
