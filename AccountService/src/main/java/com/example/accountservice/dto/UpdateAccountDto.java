package com.example.accountservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateAccountDto(
        @NotNull UUID accountId,
        @NotBlank String accountName
) {
}
