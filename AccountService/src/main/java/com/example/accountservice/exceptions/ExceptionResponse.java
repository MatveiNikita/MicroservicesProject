package com.example.accountservice.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionResponse(
        String message,
        String uri,
        HttpStatus statusCode
) {
}
