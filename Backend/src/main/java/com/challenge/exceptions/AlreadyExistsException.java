package com.challenge.exceptions;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String msg) {
        super(msg);
    }
}