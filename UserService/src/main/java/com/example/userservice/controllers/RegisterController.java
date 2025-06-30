package com.example.userservice.controllers;

import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody RegistrationUserDto registrationUserDto){
        User user = userService.registrationUser(registrationUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
