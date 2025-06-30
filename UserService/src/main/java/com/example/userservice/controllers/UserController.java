package com.example.userservice.controllers;

import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users", produces = {"application/json"})
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost/user-api")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody UpdateUserDto updateUserDto){
        User user = userService.updateUser(id, updateUserDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
