package com.example.accountservice.dto;

import java.util.UUID;

public record UpdateAccountDto(
        UUID accountId,
        String accountName
) {
}
