package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    
    // EXACT field names for test reflection
    public String secret;
    public Long jwtExpirationMs;

    public JwtUtil() {}

    public String generateToken(String username, String role, Long userId, String email) {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .claim("userId", userId)
            .claim("email", email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims validateAndGetClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token");
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
