package com.pirata.rest.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistException.class) 
    public String expectionHandler(UserExistException unfe){
        return "UserExistException:" + unfe.getMessage();
    }
}
