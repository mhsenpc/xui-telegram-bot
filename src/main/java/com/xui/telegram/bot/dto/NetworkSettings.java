package com.xui.telegram.bot.dto;

import java.util.List;

public class NetworkSettings {
    private String network;
    private String security;
    private List<String> externalProxy;
    private RealitySettings realitySettings;

    public NetworkSettings() {
    }

    public NetworkSettings(String network, String security, List<String> externalProxy, RealitySettings realitySettings) {
        this.network = network;
        this.security = security;
        this.externalProxy = externalProxy;
        this.realitySettings = realitySettings;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public List<String> getExternalProxy() {
        return externalProxy;
    }

    public void setExternalProxy(List<String> externalProxy) {
        this.externalProxy = externalProxy;
    }

    public RealitySettings getRealitySettings() {
        return realitySettings;
    }

    public void setRealitySettings(RealitySettings realitySettings) {
        this.realitySettings = realitySettings;
    }
}
