package com.mhsenpc.v2raybot.bot.enums;

public enum UserRole {

    NORMAL(1),
    ADMIN(7);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
