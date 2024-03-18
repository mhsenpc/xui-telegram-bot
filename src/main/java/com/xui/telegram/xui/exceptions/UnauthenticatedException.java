package com.xui.telegram.xui.exceptions;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super("Authentication to panel was not successful");
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
