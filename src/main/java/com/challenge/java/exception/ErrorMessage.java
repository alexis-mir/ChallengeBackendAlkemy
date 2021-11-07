package com.challenge.java.exception;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Alexis
 */
@Data
public class ErrorMessage {
    private String path;
    private String message;
    private String exception;
    private LocalDateTime timestamp;

    public ErrorMessage(Exception exception,String path){
        this.exception = exception.getClass().getSimpleName();
        this.message =  exception.getMessage();
        this.path =  path;
        this.timestamp = LocalDateTime.now();
    }
}
