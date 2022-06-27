package com.jeantravassos.subscriptionsservice.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler({MissingServletRequestParameterException.class, IllegalArgumentException.class})
    public ResponseEntity<GenericError> handleIllegalArguments(Exception e) {
        return ResponseEntity.badRequest().body(
                GenericError.builder()
                .timestamp(new Date())
                .exception(e.getClass().getCanonicalName())
                .message(e.getMessage())
                .build()
        );
    }
    
}