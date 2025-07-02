package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CreateUserDto(
        @NotBlank String name,
        @Email String email) {
}
