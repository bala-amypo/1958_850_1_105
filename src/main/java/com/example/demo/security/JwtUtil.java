package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

// Wrapper class to mimic getBody() for tests
class ClaimsWrapper {
    private final Claims claims;

    public ClaimsWrapper(Claims claims) {
        this.claims = claims;
    }

    public Claims getBody() {
        return claims;
    }
}

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "ThisIsAVeryStrongJwtSecretKeyWithMoreThan32Chars!".getBytes()
    );

    // generate token (single arg)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // generate token (4 args) for AuthTests
    public String generateToken(String username, String role, Long id, String email) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", id)
                .claim("email", email)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // validate token and return ClaimsWrapper with getBody()
    public ClaimsWrapper validateAndGetClaims(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return new ClaimsWrapper(jws.getBody());
    }
}
