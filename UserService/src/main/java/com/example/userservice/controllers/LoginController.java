package com.example.userservice.controllers;

import com.example.userservice.dto.JwtResponse;
import com.example.userservice.dto.LoginRequestDto;
import com.example.userservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RabbitTemplate rabbitTemplate;

    private final String notificationExchange = "notificationExchange";
    private final String routingKey = "notification.key";

    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                    .unauthenticated(loginRequestDto.email(), loginRequestDto.password());

            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

            String jwt = jwtUtils.generateJwtFromEmail(authenticationResponse.getName());
            String message = "User with email: " + loginRequestDto.email() + " successfully logged";
            rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse("Invalid credentials"));
        } catch (LockedException ex) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(new JwtResponse("Account is locked"));
        } catch (DisabledException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JwtResponse("Account is disabled"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JwtResponse("Unexpected error: " + ex.getMessage()));
        }
    }
}
