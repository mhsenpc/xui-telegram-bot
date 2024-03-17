package com.xui.telegram.bot.dto;

import java.util.List;

public class NetworkSettings {
    private String network;
    private String security;
    private List<String> externalProxy;
    private RealitySettings realitySettings;

    public String getNetwork() {
        return network;
    }

    public String getSecurity() {
        return security;
    }

    public List<String> getExternalProxy() {
        return externalProxy;
    }

    public RealitySettings getRealitySettings() {
        return realitySettings;
    }
}
