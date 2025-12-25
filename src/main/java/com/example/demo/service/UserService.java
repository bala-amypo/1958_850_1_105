package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;

public interface UserService {
    User registerUser(User user);
    String login(AuthRequest request);
    User findByEmail(String email);
    User updateUser(User user);
}
