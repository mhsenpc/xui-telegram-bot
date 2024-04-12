package com.mhsenpc.v2raybot.telegram.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineQuery {
    private String id;
    private User from;
    private String query;
    private String offset;
    @JsonProperty("chat_type")
    private String chatType;

    public InlineQuery(String id, User from, String query, String offset, String chatType) {
        this.id = id;
        this.from = from;
        this.query = query;
        this.offset = offset;
        this.chatType = chatType;
    }

    // Getters and setters

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    @Override
    public String toString() {
        return "InlineQuery{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", query='" + query + '\'' +
                ", offset='" + offset + '\'' +
                ", chatType='" + chatType + '\'' +
                '}';
    }
}