package com.challenge.java.exception;

/**
 * @author Alexis
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) {
        super(msg);
    }
}
