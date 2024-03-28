package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TestConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testConfigId;

    private String chatId;

    private String url;

    private Date createdAt;

    // Constructors, Getters, and Setters
    public TestConfig() {
        // Default constructor
    }

    public TestConfig(String chatId, String url, Date createdAt) {
        this.chatId = chatId;
        this.url = url;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getTestConfigId() {
        return testConfigId;
    }

    public void setTestConfigId(int testConfigId) {
        this.testConfigId = testConfigId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}