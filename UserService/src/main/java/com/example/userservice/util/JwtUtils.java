package com.example.userservice.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final Key secretKey;
    private final long expireTimeMs
            ;
    public JwtUtils(@Value("${jwt.secret}") String key, @Value("${jwt.expire.time}") long expire) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
        this.expireTimeMs = expire;
    }

    public String generateJwtFromEmail(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expireTimeMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getIdFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }
}
