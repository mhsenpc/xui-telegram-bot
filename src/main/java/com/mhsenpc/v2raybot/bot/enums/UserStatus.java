package com.mhsenpc.v2raybot.bot.enums;

public enum UserStatus {

    ACTIVE(1),
    INACTIVE(-1);

    private final int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
