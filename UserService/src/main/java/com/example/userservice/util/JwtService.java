package com.example.userservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtils jwtUtils;

    public String createToken(String userEmail) {
        return jwtUtils.generateJwtFromEmail(userEmail);
    }

    public boolean validToken(String token) {
        return jwtUtils.validateToken(token);
    }

    public String extractUserEmail(String token) {
        return jwtUtils.getEmailFromToken(token);
    }
}
