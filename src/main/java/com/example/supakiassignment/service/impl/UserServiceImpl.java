package com.example.supakiassignment.service.impl;

import com.example.supakiassignment.dto.UserCreateResultDto;
import com.example.supakiassignment.entity.User;
import com.example.supakiassignment.entity.Wallet;
import com.example.supakiassignment.repository.UserRepository;
import com.example.supakiassignment.repository.WalletRepository;
import com.example.supakiassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public UserCreateResultDto createUser(User user, Float balanceAmount) {
        try {
            if (userRepository.findByEmailAddress(user.getEmailAddress()) == null) {
                user.setActive(true);
                User userCreated = userRepository.save(user);

                Wallet wallet = Wallet.builder()
                        .userId(userCreated.getId())
                        .balance(balanceAmount)
                        .build();

                Wallet walletSaved = walletRepository.save(wallet);

                return UserCreateResultDto.builder()
                        .id(user.getId())
                        .msg("User created successfully")
                        .build();
            } else {
                log.error("User already exists with the entered email address = {}", user.getEmailAddress());
                return UserCreateResultDto.builder()
                        .msg("User already exists")
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
