package com.mhsenpc.v2raybot.bot.enums;

public enum PaymentMethod {
    TRANSFER_MONEY(1),
    WALLET(2),
    COUPON(3);

    private final int value;

    PaymentMethod(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
