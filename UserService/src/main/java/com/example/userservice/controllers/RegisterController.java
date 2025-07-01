package com.example.userservice.controllers;

import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;

    private final String notificationExchange = "notificationExchange";
    private final String routingKey = "notification.key";


    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody RegistrationUserDto registrationUserDto){
        User user = userService.registrationUser(registrationUserDto);
        String message = "User : " + registrationUserDto + " successfully register";
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
