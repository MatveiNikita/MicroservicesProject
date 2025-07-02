package com.example.userservice.controllers;

import com.example.userservice.dto.JwtResponse;
import com.example.userservice.dto.LoginRequestDto;
import com.example.userservice.util.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RabbitTemplate rabbitTemplate;

    private final String notificationExchange = "notificationExchange";
    private final String routingKey = "notification.key";

    @SneakyThrows
    @PostMapping
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                    .unauthenticated(loginRequestDto.email(), loginRequestDto.password());

            Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

            String jwt = jwtUtils.generateJwtFromEmail(authenticationResponse.getName());
            String message = "User with email: " + loginRequestDto.email() + " successfully logged";
            rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("invalid credentials");
        } catch (LockedException ex) {
            throw new LoginException("Account is locked");
        } catch (DisabledException ex) {
            throw  new DisabledException("Account is disable");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JwtResponse("Unexpected error: " + ex.getMessage()));
        }
    }
}
