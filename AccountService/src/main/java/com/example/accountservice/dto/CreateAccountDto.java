package com.example.accountservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountDto(
        @NotBlank String accountName,
        @NotBlank String handlerId
) {
}
