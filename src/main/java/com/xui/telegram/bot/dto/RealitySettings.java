package com.xui.telegram.bot.dto;

import java.util.List;

public class RealitySettings {

    private boolean show;
    private int xver;
    private String dest;
    private List<String> serverNames;
    private String privateKey;
    private String minClient;
    private String maxClient;
    private int maxTimediff;
    private List<String> shortIds;
    private InboundSettings settings;

    public boolean isShow() {
        return show;
    }

    public int getXver() {
        return xver;
    }

    public String getDest() {
        return dest;
    }

    public List<String> getServerNames() {
        return serverNames;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getMinClient() {
        return minClient;
    }

    public String getMaxClient() {
        return maxClient;
    }

    public int getMaxTimediff() {
        return maxTimediff;
    }

    public List<String> getShortIds() {
        return shortIds;
    }

    public InboundSettings getSettings() {
        return settings;
    }
}
