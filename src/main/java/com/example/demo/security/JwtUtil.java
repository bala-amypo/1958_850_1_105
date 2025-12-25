package com.example.demo.security;

public class JwtUtil {

    public String generateToken(String username) {
        return "dummy-token";
    }

    public String extractUsername(String token) {
        return "user";
    }
}
