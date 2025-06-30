package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;


public record CreateUserDto(
        @NotBlank String name,
        @NotBlank String email) {
}
