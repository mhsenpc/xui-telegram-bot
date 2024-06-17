package com.mhsenpc.v2raybot.xui.dto;

public class ClientStat {
    private int id;
    private int inboundId;
    private boolean enable;
    private String email;
    private long up;
    private long down;
    private long expiryTime;
    private long total;
    private int reset;

    public int getId() {
        return id;
    }

    public int getInboundId() {
        return inboundId;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getEmail() {
        return email;
    }

    public long getUp() {
        return up;
    }

    public long getDown() {
        return down;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public long getTotal() {
        return total;
    }

    public int getReset() {
        return reset;
    }
}
