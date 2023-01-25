package com.example.supakiassignment.controller;

import com.example.supakiassignment.dto.PageResponse;
import com.example.supakiassignment.dto.ResponseDto;
import com.example.supakiassignment.entity.Item;
import com.example.supakiassignment.service.StoreService;
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
@RequestMapping("/supaki")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @GetMapping(value = "/listings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<PageResponse<Item>>> listingsApi(@RequestParam(defaultValue = "0") String page,
                                                 @RequestParam(defaultValue = "10") String size) {
        try {
            PageResponse<Item> pageResp = storeService.fetchAll(Integer.parseInt(page), Integer.parseInt(size));
            log.info("The response item param = {}", pageResp.getTotalPages());
            ResponseDto<PageResponse<Item>> resp = ResponseDto.<PageResponse<Item>>builder()
                    .data(pageResp)
                    .build();
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch(Exception e) {
            log.error("Encountered an exception while fetch store items = {}", e);
            return new ResponseEntity<>(ResponseDto.<PageResponse<Item>>builder()
                    .error("Unable to fetch the items from the store")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
