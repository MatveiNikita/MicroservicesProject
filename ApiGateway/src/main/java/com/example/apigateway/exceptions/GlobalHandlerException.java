package com.example.apigateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handExceptions(){
        ExceptionResponse response = new ExceptionResponse(
                "WE DON'T KNOW WHAT IS IT... IF WE COULD KNOW, BUT WE DON'T KNOW (SOMETHING WRONG AT AccountService)",
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
