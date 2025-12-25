package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String secret = "ThisIsAVeryStrongJwtSecretKeyWithMoreThan32Chars!";

    // simple single-argument method (optional)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // overloaded method required by AuthTests
    public String generateToken(String username, String role, Long id, String email) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", id)
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // required by AuthTests
    public Claims validateAndGetClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
