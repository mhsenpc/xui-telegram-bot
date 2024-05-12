package com.mhsenpc.v2raybot.xui.dto;

import java.util.List;

public class StreamSettings {
    private String network;
    private String security;
    private List<String> externalProxy;
    private RealitySettings realitySettings;
    private TcpSettings tcpSettings;

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

    public TcpSettings getTcpSettings() {
        return tcpSettings;
    }
}
