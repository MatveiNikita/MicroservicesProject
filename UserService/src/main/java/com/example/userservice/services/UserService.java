package com.example.userservice.services;

import com.example.userservice.dto.CreateUserDto;
import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.models.User;

import java.util.UUID;


public interface UserService {
    User registrationUser(RegistrationUserDto registrationUserDto);
    User getUserById(UUID id);
    User updateUser(UUID id, UpdateUserDto updateUserDto);
    void deleteUserById(UUID id);
    User getCurrentUser(); // можно получить по email из SecurityContext

}
