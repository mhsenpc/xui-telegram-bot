package com.xui.telegram.xui.exceptions;

public class CookieNotFoundException extends RuntimeException{

    public CookieNotFoundException() {
        super("Cookie not found in the storage");
    }

    public CookieNotFoundException(String message) {
        super(message);
    }
}
