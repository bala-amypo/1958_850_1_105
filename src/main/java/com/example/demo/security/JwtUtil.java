package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor("ThisIsAVeryStrongJwtSecretKeyWithMoreThan32Chars!".getBytes());

    // single-arg token generation
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // overloaded token generation for test cases
    public String generateToken(String username, String role, Long id, String email) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", id)
                .claim("email", email)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // validate token and get Claims
    public Claims validateAndGetClaims(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return jws.getBody();
    }
}
