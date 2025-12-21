package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(Long id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    List<User> getAllUsers();
}
