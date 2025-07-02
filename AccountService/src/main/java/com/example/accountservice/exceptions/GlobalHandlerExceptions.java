package com.example.accountservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception){
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handNullPointerException(HttpServletRequest request, NullPointerException exception){
        ExceptionResponse response = new ExceptionResponse("FIELDS CANNOT BE NULL", request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handMethodArgumentNotValidException(HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse("VERIFY YOUR INPUT FIELDS", request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception){
        ExceptionResponse response = new ExceptionResponse(exception.getLocalizedMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
