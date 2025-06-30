package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountDto(
    @NotBlank String accountName,
    @NotBlank String handlerEmail
){ }
