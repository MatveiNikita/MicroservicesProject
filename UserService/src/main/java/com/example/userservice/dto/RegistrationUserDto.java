package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationUserDto(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
