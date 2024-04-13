package com.mhsenpc.v2raybot.bot.enums;

public enum OrderStatus {
    PENDING(1),
    CONFIRMED(2),
    REJECTED(3);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
