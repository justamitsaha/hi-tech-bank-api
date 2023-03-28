package com.saha.amit.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        System.out.println("<-----Inside handleAllExceptions---->" + ex);
        String error = ex.getMessage();
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("<----Inside handleMethodArgumentNotValid---->" + ex.getMessage());

        String message = ex.getBindingResult().getAllErrors().stream().map(s -> s.getDefaultMessage())
                .collect(Collectors.joining("|"));
        return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
    }
}
