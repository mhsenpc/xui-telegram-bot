package com.mhsenpc.v2raybot.xui.exceptions;

public class InvalidXUIConfig extends RuntimeException {
    public InvalidXUIConfig() {
        super("The provided credentials for xui panel is not valid");
    }

    public InvalidXUIConfig(String message) {
        super(message);
    }
}
