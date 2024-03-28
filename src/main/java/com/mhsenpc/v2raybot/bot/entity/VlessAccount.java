package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vless_accounts")
public class VlessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vlessAccountId;

    private String name;

    private int connectionLimit;

    private float trafficLimit;

    private int days;

    private String url;

    private int userId;

    private String uuid;

    private Date createdAt;

    private Date validUntil;


    public int getVlessAccountId() {
        return vlessAccountId;
    }

    public void setVlessAccountId(int vlessAccountId) {
        this.vlessAccountId = vlessAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConnectionLimit() {
        return connectionLimit;
    }

    public void setConnectionLimit(int connectionLimit) {
        this.connectionLimit = connectionLimit;
    }

    public float getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(float trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}