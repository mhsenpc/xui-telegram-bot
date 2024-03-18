package com.xui.telegram.xui.dto;

public class Client {
    private String id;
    private String flow;
    private String email;
    private int limitIp;
    private int totalGB;
    private long expiryTime;
    private boolean enable;
    private String tgId;
    private String subId;
    private int reset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLimitIp() {
        return limitIp;
    }

    public void setLimitIp(int limitIp) {
        this.limitIp = limitIp;
    }

    public int getTotalGB() {
        return totalGB;
    }

    public void setTotalGB(int totalGB) {
        this.totalGB = totalGB;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTgId() {
        return tgId;
    }

    public void setTgId(String tgId) {
        this.tgId = tgId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }
}
