package com.example.supakiassignment.repository;

import com.example.supakiassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAddress(String emailAddress);
}
