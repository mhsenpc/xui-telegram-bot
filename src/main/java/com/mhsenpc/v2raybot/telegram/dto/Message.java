package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhsenpc.v2raybot.telegram.interfaces.IResponse;

public class Message implements IResponse {
    @JsonProperty("message_id")
    private int messageId;
    private String text;
    private User from;
    @JsonProperty("sender_chat")
    private Chat senderChat;
    private int date;
    private Chat chat;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Chat getSenderChat() {
        return senderChat;
    }

    public void setSenderChat(Chat senderChat) {
        this.senderChat = senderChat;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", text='" + text + '\'' +
                ", from=" + from +
                ", senderChat=" + senderChat +
                ", date=" + date +
                ", chat=" + chat +
                '}';
    }
}
