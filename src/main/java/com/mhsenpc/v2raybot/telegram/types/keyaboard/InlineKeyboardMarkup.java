package com.mhsenpc.v2raybot.telegram.types.keyaboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineKeyboardMarkup implements IReplyMarkup {
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();


    public void addRow(InlineKeyboardButton inlineKeyboardButton){
        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(inlineKeyboardButton);
        this.keyboardList.add(list);
    }

    public void addRow(InlineKeyboardButton keyboardButton1, InlineKeyboardButton keyboardButton2){
        List<InlineKeyboardButton> list = Arrays.asList(keyboardButton1, keyboardButton2);
        this.keyboardList.add(list);
    }

    public List<List<InlineKeyboardButton>> getKeyboardList() {
        return this.keyboardList;
    }

    public void setKeyboardList(List<List<InlineKeyboardButton>> keyboardList) {
        this.keyboardList = keyboardList;
    }

    @Override
    public String toString() {
        return "InlineKeyboardMarkup{" +
                "keyboardList=" + keyboardList +
                '}';
    }
}
