package com.mhsenpc.v2raybot.xui.exceptions;

public class CookieNotFoundException extends RuntimeException{

    public CookieNotFoundException() {
        super("Cookie not found in the storage");
    }

    public CookieNotFoundException(String message) {
        super(message);
    }
}
