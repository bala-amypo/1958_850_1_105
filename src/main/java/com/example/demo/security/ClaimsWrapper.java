package com.example.demo.security;

import io.jsonwebtoken.Claims;

// Must be public so test can access it
public class ClaimsWrapper {

    private final Claims claims;

    public ClaimsWrapper(Claims claims) {
        this.claims = claims;
    }

    public Claims getBody() {
        return claims;
    }
}
