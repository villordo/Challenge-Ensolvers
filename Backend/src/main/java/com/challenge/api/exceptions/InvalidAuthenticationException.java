package com.challenge.api.exceptions;

public class InvalidAuthenticationException extends Exception {
    public InvalidAuthenticationException(String message){
        super(message);
    }

}
