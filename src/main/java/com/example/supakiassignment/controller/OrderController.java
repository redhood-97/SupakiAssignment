package com.example.supakiassignment.controller;

import com.example.supakiassignment.dto.OrderPlaceRequestDto;
import com.example.supakiassignment.dto.OrderPlaceResultDto;
import com.example.supakiassignment.dto.PageResponse;
import com.example.supakiassignment.dto.ResponseDto;
import com.example.supakiassignment.entity.Order;
import com.example.supakiassignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> placeAnOrder(@RequestBody OrderPlaceRequestDto orderPlaceRequestDto) {
        try {
            OrderPlaceResultDto res = orderService.placeOrder(orderPlaceRequestDto.getUserId(), orderPlaceRequestDto.getItemId());
            return new ResponseEntity<>(ResponseDto.builder().data(res).build(), HttpStatus.OK);
        }catch(Exception e) {
            log.error("Error encountered while puchasing item ", e);
            return new ResponseEntity<>(ResponseDto.builder()
                            .data("Error occured while ordering").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> getAllOrders(@RequestParam(name = "userId") String userId,
                                                    @RequestParam(name = "page") String page,
                                                    @RequestParam(name = "size") String size) {
        try {
            PageResponse<Order> res = orderService.findAllOrders(userId, Integer.parseInt(page), Integer.parseInt(size));
            return new ResponseEntity<>(ResponseDto.builder().data(res).build(), HttpStatus.OK);
        }catch(Exception e) {
            log.error("Error encountered while fetching all orders ", e);
            return new ResponseEntity<>(ResponseDto.builder().data("Error loading the orders").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
