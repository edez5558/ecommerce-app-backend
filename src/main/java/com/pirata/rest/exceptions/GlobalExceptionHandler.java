package com.pirata.rest.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class) 
    public String expectionHandler(UserException unfe){
        return "UserException:" + unfe.getMessage();
    }

    @ExceptionHandler(ProductException.class) 
    public String expectionHandler(ProductException unfe){
        return "ProductException:" + unfe.getMessage();
    }
}
