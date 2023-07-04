package com.apper.estore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Provider;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleInvalidField(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }


    @ExceptionHandler(InvalidUserAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleInvalidUserAgeException(InvalidUserAgeException ex){
        return new ServiceError(ex.getMessage());
    }

}