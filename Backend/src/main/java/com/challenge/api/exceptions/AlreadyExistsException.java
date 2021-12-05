package com.challenge.api.exceptions;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String msg) {
        super(msg);
    }
}