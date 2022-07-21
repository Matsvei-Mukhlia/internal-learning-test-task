package com.example.internallearningtesttask.exceptionhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionDetails {

    private String message;
    private String details;
    private LocalDateTime timestamp;

}
