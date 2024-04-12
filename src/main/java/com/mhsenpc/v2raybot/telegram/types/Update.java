package com.mhsenpc.v2raybot.telegram.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Update {

    @JsonProperty("update_id")
    private int updateId;
    private Message message;
    @JsonProperty("inline_query")
    private InlineQuery inlineQuery;
    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public InlineQuery getInlineQuery() {
        return inlineQuery;
    }

    public void setInlineQuery(InlineQuery inlineQuery) {
        this.inlineQuery = inlineQuery;
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public void setCallbackQuery(CallbackQuery callbackQuery) {
        this.callbackQuery = callbackQuery;
    }

    @Override
    public String toString() {
        return "Update{" +
                "updateId=" + updateId +
                ", message=" + message +
                ", inlineQuery=" + inlineQuery +
                ", callbackQuery=" + callbackQuery +
                '}';
    }
}
