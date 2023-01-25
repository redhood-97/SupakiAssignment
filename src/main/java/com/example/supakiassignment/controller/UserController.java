package com.example.supakiassignment.controller;

import com.example.supakiassignment.dto.CreateUserRequestDto;
import com.example.supakiassignment.dto.UserCreateResultDto;
import com.example.supakiassignment.entity.User;
import com.example.supakiassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreateResultDto> create(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserCreateResultDto res = new UserCreateResultDto();
        try {
            // Using as an adapter for the service layer
            User obj = User.builder()
                    .emailAddress(createUserRequestDto.getEmailAddress())
                    .name(createUserRequestDto.getName())
                    .active(true)
                    .build();
            res =  userService.createUser(obj, createUserRequestDto.getInitialBalanceAmount());
            log.info("The status message for user creation = {}", res.getMsg());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e) {
            log.error("Error encountered while creating the user ", e);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
