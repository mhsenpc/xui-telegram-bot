package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TestConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_config_id")
    private int testConfigId;

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "code", nullable = false, length = 800)
    private String code;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    // Constructors, Getters, and Setters
    public TestConfig() {
        // Default constructor
    }

    public TestConfig(String chatId, String code, Date createdAt) {
        this.chatId = chatId;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}