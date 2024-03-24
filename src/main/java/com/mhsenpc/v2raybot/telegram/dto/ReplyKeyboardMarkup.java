package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplyKeyboardMarkup {
    @JsonProperty("keyboard")
    private List<List<KeyboardButton>> keyboardList = new ArrayList<>();

    @JsonProperty("is_persistent")
    private boolean isPersistent;
    @JsonProperty("resize_keyboard")
    private boolean resizeKeyboard;

    @JsonProperty("one_time_keyboard")
    private boolean oneTimeKeyboard;

    public void addRow(KeyboardButton keyboardButton){
        List<KeyboardButton> list = new ArrayList<>();
        list.add(keyboardButton);
        this.keyboardList.add(list);
    }

    public void addRow(KeyboardButton keyboardButton1, KeyboardButton keyboardButton2){
        List<KeyboardButton> list = Arrays.asList(keyboardButton1, keyboardButton2);
        this.keyboardList.add(list);
    }

    public List<List<KeyboardButton>> getKeyboardList() {
        return this.keyboardList;
    }

    public void setKeyboardList(List<List<KeyboardButton>> keyboardList) {
        this.keyboardList = keyboardList;
    }

    public boolean isPersistent() {
        return isPersistent;
    }

    public void setPersistent(boolean persistent) {
        isPersistent = persistent;
    }

    public boolean isResizeKeyboard() {
        return resizeKeyboard;
    }

    public void setResizeKeyboard(boolean resizeKeyboard) {
        this.resizeKeyboard = resizeKeyboard;
    }

    public boolean isOneTimeKeyboard() {
        return oneTimeKeyboard;
    }

    public void setOneTimeKeyboard(boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = oneTimeKeyboard;
    }
}
