package com.mhsenpc.v2raybot.xui.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Inbound {
    private int id;
    private int up;
    private int down;
    private int total;
    private String remark;
    private boolean enable;
    private long expiryTime;
    private List<ClientStat> clientStats;
    private String listen;
    private int port;
    private String protocol;
    private String settings;
    private String streamSettings;
    private String tag;
    private String sniffing;

    public int getId() {
        return id;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getTotal() {
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

    public List<ClientStat> getClientStats() {
        return clientStats;
    }

    public String getListen() {
        return listen;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getSettings() {
        return settings;
    }

    public StreamSettings getStreamSettings(){

        if(streamSettings == null || streamSettings.isEmpty()){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(this.streamSettings, StreamSettings.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getTag() {
        return tag;
    }

    public String getSniffing() {
        return sniffing;
    }

}