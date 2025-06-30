package com.example.userservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseException> responseStatusExceptionHandler(ResponseStatusException exception, HttpServletRequest request){
        ResponseException responseException = new ResponseException(
                exception.getStatusCode().toString(),
                exception.getReason(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseException);
    };

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseException> responsePSQLExceptionHandler(SQLException exception, HttpServletRequest request){
        ResponseException responseException = new ResponseException(
                new String(exception.getSQLState()),
                exception.getLocalizedMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(exception.getErrorCode()).body(responseException);
    }
}
