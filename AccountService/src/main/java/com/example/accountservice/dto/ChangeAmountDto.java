package com.example.accountservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ChangeAmountDto(
        @NotNull UUID uuid,
       @Positive BigDecimal amount
) {}
