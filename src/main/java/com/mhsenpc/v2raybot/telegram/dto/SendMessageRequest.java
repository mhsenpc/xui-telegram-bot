package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageRequest extends APIRequest {

    @JsonProperty("chat_id")
    private long chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("disable_notification")
    private boolean disableNotification;

    @JsonProperty("reply_markup")
    private ReplyKeyboardMarkup replyMarkup;

    public SendMessageRequest() {
    }

    public SendMessageRequest(long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public void setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
    }

    public ReplyKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(ReplyKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }
}
