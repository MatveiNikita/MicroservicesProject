package com.example.userservice.exceptions;

import java.time.LocalDateTime;

public record ResponseException(
        String statusCode,
        String message,
        String URI,
        LocalDateTime exceptionTime
) {
}
