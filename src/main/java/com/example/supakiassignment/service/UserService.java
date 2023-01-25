package com.example.supakiassignment.service;

import com.example.supakiassignment.dto.UserCreateResultDto;
import com.example.supakiassignment.entity.User;

public interface UserService {
    public UserCreateResultDto createUser(User user, Float amount);
}
