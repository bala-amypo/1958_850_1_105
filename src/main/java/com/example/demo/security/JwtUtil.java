package com.example.demo.security;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;   // ✅ correct import

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${app.jwtSecret:0123456789ABCDEF0123456789ABCDEF}")
    private String secret = "0123456789ABCDEF0123456789ABCDEF";

    @Value("${app.jwtExpirationMs:3600000}")
    private long jwtExpirationMs = 3600000L;

    // For tests (ReflectionTestUtils)
    public void setSecret(String secret) { this.secret = secret; }
    public void setJwtExpirationMs(long jwtExpirationMs) { this.jwtExpirationMs = jwtExpirationMs; }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
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
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)   // HS256 so 256‑bit key is valid
                .compact();
    }

    // tests call: jwt.validateAndGetClaims(token).getBody()
    public Jws<Claims> validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
