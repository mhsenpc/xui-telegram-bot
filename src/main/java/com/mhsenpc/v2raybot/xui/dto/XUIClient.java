package com.mhsenpc.v2raybot.xui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhsenpc.v2raybot.xui.enums.Flow;

public class XUIClient {
    private String id;
    private Flow flow;
    private String email;
    private int limitIp;
    @JsonProperty("totalGB")
    private long traffic;
    private long expiryTime;
    private boolean enable = true;
    @JsonProperty("tgId")
    private String telegramID = "";
    private String subId;
    private int reset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlow() {
        return flow.getValue();
    }

    public void setFlow(Flow flow) {
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

    public long getTraffic() {
        return traffic;
    }

    public void setTraffic(long trafficInBytes) {
        this.traffic = trafficInBytes;
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

    public String getTelegramID() {
        return telegramID;
    }

    public void setTelegramID(String telegramID) {
        this.telegramID = telegramID;
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
