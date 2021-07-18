package com.mokhs.springplayground.springexception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());

        return ResponseEntity.internalServerError().body("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
