package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackQuery {
    private String id;
    private User from;
    private Message message;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    @JsonProperty("chat_instance")
    private String chatInstance;
    private String data;

    public CallbackQuery(String id, User from, Message message, String inlineMessageId, String chatInstance, String data) {
        this.id = id;
        this.from = from;
        this.message = message;
        this.inlineMessageId = inlineMessageId;
        this.chatInstance = chatInstance;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public void setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
    }

    public String getChatInstance() {
        return chatInstance;
    }

    public void setChatInstance(String chatInstance) {
        this.chatInstance = chatInstance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CallbackQuery{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", message=" + message +
                ", inlineMessageId='" + inlineMessageId + '\'' +
                ", chatInstance='" + chatInstance + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
