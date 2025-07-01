package com.example.accountservice.jwt;

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

    public JwtUtils(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expire.time}") long expire) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.EXPIRE_TIME = expire;
    }

    public String generateJwtFromEmail(String email){
        Date now = new Date();
            Date expiry = new Date(now.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }
}
