package com.example.userservice.dto;

import java.util.List;

public record PresentUser(
        String email,
        List<ViewAccountDto> accounts
) {
}
