package com.mhsenpc.v2raybot.telegram.types.keyaboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineKeyboardButton {

    private String text;
    @JsonProperty("callback_data")
    private String callbackData = "";

    public InlineKeyboardButton() {
    }

    public InlineKeyboardButton(String text) {
        this.text = text;
    }

    public InlineKeyboardButton(String text, String callbackData) {
        this.text = text;
        this.callbackData = callbackData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    @Override
    public String toString() {
        return "InlineKeyboardButton{" +
                "text='" + text + '\'' +
                ", callbackData='" + callbackData + '\'' +
                '}';
    }
}
