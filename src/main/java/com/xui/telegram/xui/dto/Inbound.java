package com.xui.telegram.xui.dto;

public class Inbound {

    private int id;
    private long up;
    private long down;
    private long total;
    private String remark;
    private boolean enable;
    private long expiryTime;
    private int port;
    private String protocol;

    public int getId() {
        return id;
    }

    public long getUp() {
        return up;
    }

    public long getDown() {
        return down;
    }

    public long getTotal() {
        return total;
    }

    public String getRemark() {
        return remark;
    }

    public boolean isEnable() {
        return enable;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }
}
