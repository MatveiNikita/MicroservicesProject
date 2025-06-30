package com.example.userservice.dto;

import jakarta.annotation.Nonnull;

public record UpdateUserDto(
        @Nonnull String username,
        @Nonnull String email
) {
}
