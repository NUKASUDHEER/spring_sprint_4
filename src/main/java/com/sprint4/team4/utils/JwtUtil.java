package com.sprint4.team4.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import javax.crypto.SecretKey;


public class JwtUtil {

    // 🔑 Must be at least 32 bytes for HS256
    private static final String SECRET = "ThisIsASecretKeyForJwtTokenWhichMustBeAtLeast32Bytes!";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24; // 1 day

    // Generate JWT
    public static String generateToken(String userId, boolean isAdmin) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("isAdmin", isAdmin)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)   // ✅ works fine
                .compact();
    }

    // Validate token
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)   // ✅ SecretKey type, no error
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract userId
    public static String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", String.class);
    }

    // Extract isAdmin
    public static boolean getIsAdmin(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("isAdmin", Boolean.class);
    }
}
