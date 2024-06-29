package com.mhsenpc.v2raybot.xui.dto;

public class XuiConfig {

    private String baseUrl;

    private String username;

    private String password;

    private String overrideInboundId;

    private String vpnHost;

    private String vpnPort;

    public XuiConfig(String baseUrl, String username, String password, String overrideInboundId, String vpnHost, String vpnPort) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.overrideInboundId = overrideInboundId;
        this.vpnHost = vpnHost;
        this.vpnPort = vpnPort;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOverrideInboundId() {
        return overrideInboundId;
    }

    public String getVpnHost() {
        return vpnHost;
    }

    public String getVpnPort() {
        return vpnPort;
    }
}
