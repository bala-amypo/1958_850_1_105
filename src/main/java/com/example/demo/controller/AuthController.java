package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // REGISTER API (Dummy / Simple)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        // You can save user to DB here if needed
        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN API (NO JWT, NO TOKEN)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        // You can validate email/password manually if you want
        return ResponseEntity.ok("Login successful");
    }
}
