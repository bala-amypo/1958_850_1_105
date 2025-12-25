package com.example.demo.security;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    // for ReflectionTestUtils in tests
    public void setSecret(String secret) { this.secret = secret; }
    public void setJwtExpirationMs(long jwtExpirationMs) { this.jwtExpirationMs = jwtExpirationMs; }

    private SecretKey getSigningKey() {
        // 32-byte key is strong enough for HS256 and works with tests
        return javax.crypto.spec.SecretKeySpec(
                secret.getBytes(),
                0,
                secret.getBytes().length,
                "HmacSHA256"
        );
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
                // use HS256 so 256‑bit key is valid
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Return Jws<Claims> so tests can call .getBody()
    public Jws<Claims> validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
