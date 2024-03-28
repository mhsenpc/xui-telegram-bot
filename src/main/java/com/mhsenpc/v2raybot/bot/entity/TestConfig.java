package com.mhsenpc.v2raybot.bot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "test_configs")
public class TestConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testConfigId;

    private String url;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Constructors, Getters, and Setters
    public TestConfig() {
        // Default constructor
    }

    public TestConfig(String url, Date createdAt) {
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