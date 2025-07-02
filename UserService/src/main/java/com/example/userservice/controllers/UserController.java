package com.example.userservice.controllers;

import com.example.userservice.dto.PresentUser;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import com.example.userservice.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users", produces = {"application/json"})
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080/user-api")
public class UserController {

    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;
    private final JwtService jwtService;

    private final String notificationExchange = "notificationExchange";
    private final String routingKey = "notification.key";

    @GetMapping
    public ResponseEntity<PresentUser>presentUser(@RequestHeader("Authorization") String token){
        System.out.println(token);
        jwtService.validToken(token);
        PresentUser presentUser = userService.presentUser(token);
        String message = "User " + presentUser;
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
        return new ResponseEntity<>(presentUser, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id){
        User user = userService.getUserById(id);
        String message = "Get User by id: " + id.toString() + " successfully";
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody UpdateUserDto updateUserDto){
        User user = userService.updateUser(id, updateUserDto);
        String message = "Update User: " + user + " successfully";
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
        String message = "Delete User : " + id.toString() + " successfully";
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
