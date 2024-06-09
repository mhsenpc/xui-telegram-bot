package com.mhsenpc.v2raybot.bot.enums;

public enum PaymentMethod {
    WALLET(2);

    private final int value;

    PaymentMethod(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
