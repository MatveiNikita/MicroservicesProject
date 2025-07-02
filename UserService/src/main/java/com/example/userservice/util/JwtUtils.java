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

    private final Key SECRET_KEY;
    private final long EXPIRE_TIME;
            ;
    public JwtUtils(@Value("${jwt.secret}") String key, @Value("${jwt.expire.time}") long expire) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(key.getBytes());
        this.EXPIRE_TIME = expire;
    }

    public String generateJwtFromEmail(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token){
        if (token != null && token.startsWith("Bearer ")){
            token = token.substring(7).trim();
        }
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }
}
