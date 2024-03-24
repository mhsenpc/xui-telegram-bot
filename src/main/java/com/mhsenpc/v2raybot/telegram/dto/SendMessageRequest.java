package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageRequest extends APIRequest {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("disable_notification")
    private boolean disableNotification;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reply_markup")
    private ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();

    public SendMessageRequest() {
        super();
        this.setMethod("sendMessage");
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
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

    @Override
    public String toString() {
        return "SendMessageRequest{" +
                "chatId='" + chatId + '\'' +
                ", text='" + text + '\'' +
                ", disableNotification=" + disableNotification +
                ", replyMarkup=" + replyMarkup +
                ", queryParams=" + queryParams +
                '}';
    }
}
