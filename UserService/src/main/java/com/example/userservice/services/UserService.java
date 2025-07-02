package com.example.userservice.services;

import com.example.userservice.dto.PresentUser;
import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.models.User;
import org.springframework.security.core.context.SecurityContext;

import java.util.UUID;


public interface UserService {
    User registrationUser(RegistrationUserDto registrationUserDto);
    User getUserById(UUID id);
    User updateUser(UUID id, UpdateUserDto updateUserDto);
    PresentUser presentUser(String token);
    void deleteUserById(UUID id);
    User getCurrentUser(); // можно получить по email из SecurityContext

}
