package com.example.accountservice.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtils jwtUtils;

    public boolean isTokenValid(String token){
        return jwtUtils.validateToken(token);
    }

    public String extractUserEmail(String token){
     return jwtUtils.generateJwtFromEmail(token);
    }
}
