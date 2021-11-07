package com.challenge.java.exception;

/**
 * @author Alexis
 */
public class NoAuthorizeException extends RuntimeException{
    NoAuthorizeException(String msg){
        super(msg);
    }
}
