package com.challenge.java.exception;

/**
 * @author Alexis
 */
public class BadRequestException extends RuntimeException{
    BadRequestException(String msg){
        super(msg);
    }
}
