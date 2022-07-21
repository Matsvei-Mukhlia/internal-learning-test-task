package com.example.internallearningtesttask.exceptionhandler.controller;


import com.example.internallearningtesttask.exceptionhandler.exception.ExceptionDetails;
import com.example.internallearningtesttask.externalhrsource.exception.ExternalHrSourceParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExternalHrSourceParseException.class)
    protected ResponseEntity<ExceptionDetails> handleExternalHrSourceParseException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ExceptionDetails(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
