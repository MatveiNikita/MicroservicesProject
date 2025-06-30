package com.example.userservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViewAccountDto(
        String accountName,
        BigDecimal balance,
        LocalDateTime createdTime
) {
}
