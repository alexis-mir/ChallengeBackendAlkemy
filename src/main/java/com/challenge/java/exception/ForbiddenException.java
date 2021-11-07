package com.challenge.java.exception;

/**
 * @author Alexis
 */
public class ForbiddenException extends RuntimeException{
    ForbiddenException(String msg){
        super(msg);
    }
}
