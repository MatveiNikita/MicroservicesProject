package com.example.userservice.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String statusCode,
        String message,
        String URI,
        LocalDateTime exceptionTime
) {
}
