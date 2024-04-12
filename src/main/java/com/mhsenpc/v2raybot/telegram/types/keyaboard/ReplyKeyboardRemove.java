package com.mhsenpc.v2raybot.telegram.types.keyaboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyKeyboardRemove implements IReplyMarkup {

    @JsonProperty("remove_keyboard")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean removeKeyboard;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean selective;

    public ReplyKeyboardRemove() {
    }

    public ReplyKeyboardRemove(boolean removeKeyboard) {
        this.removeKeyboard = removeKeyboard;
    }

    public boolean isRemoveKeyboard() {
        return removeKeyboard;
    }

    public void setRemoveKeyboard(boolean removeKeyboard) {
        this.removeKeyboard = removeKeyboard;
    }

    public boolean isSelective() {
        return selective;
    }

    public void setSelective(boolean selective) {
        this.selective = selective;
    }

    @Override
    public String toString() {
        return "ReplyKeyboardRemove{" +
                "removeKeyboard=" + removeKeyboard +
                ", selective=" + selective +
                '}';
    }
}
