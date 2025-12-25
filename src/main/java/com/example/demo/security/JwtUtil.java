package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    @Value("${app.jwtSecret:0123456789ABCDEF0123456789ABCDEF}")
    private String secret = "0123456789ABCDEF0123456789ABCDEF";
    
    @Value("${app.jwtExpirationMs:3600000}")
    private long jwtExpirationMs = 3600000L;
    
    // For ReflectionTestUtils in tests
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public void setJwtExpirationMs(long jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public String generateToken(String username, String role, Long userId, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        claims.put("email", email);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }
    
    // EXACT TEST EXPECTATION: returns Jws<Claims> so .getBody() works
    public io.jsonwebtoken.Jws<io.jsonwebtoken.Claims> validateAndGetClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
