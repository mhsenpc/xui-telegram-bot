package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vless_accounts")
public class VlessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vless_account_id")
    private int vlessAccountId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "connection_limit", nullable = false)
    private int connectionLimit;

    @Column(name = "traffic_limit", nullable = false)
    private float trafficLimit;

    @Column(name = "days", nullable = false)
    private int days;

    @Column(name = "vless_code", nullable = false)
    private String vlessCode;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "valid_until", nullable = false)
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

    public String getVlessCode() {
        return vlessCode;
    }

    public void setVlessCode(String vlessCode) {
        this.vlessCode = vlessCode;
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