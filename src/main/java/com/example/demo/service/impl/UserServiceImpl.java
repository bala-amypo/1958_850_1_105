package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> { throw new IllegalArgumentException("Username already exists"); });

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException("Email already exists"); });

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole() == null ? "USER" : request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole(),
                user.getId(),
                user.getEmail()
        );

        return new AuthResponse(token);
    }
}
