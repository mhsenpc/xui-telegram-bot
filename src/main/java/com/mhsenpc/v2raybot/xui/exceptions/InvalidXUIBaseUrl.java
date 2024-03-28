package com.mhsenpc.v2raybot.xui.exceptions;

public class InvalidXUIBaseUrl extends RuntimeException {
    public InvalidXUIBaseUrl() {
        super("The provided base url for xui panel is not valid");
    }

    public InvalidXUIBaseUrl(String message) {
        super(message);
    }
}
