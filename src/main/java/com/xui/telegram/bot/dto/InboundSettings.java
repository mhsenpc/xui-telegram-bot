package com.xui.telegram.bot.dto;

public class InboundSettings {
    private String publicKey;
    private String fingerprint;
    private String serverName;
    private String spiderX;

    public String getPublicKey() {
        return publicKey;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getServerName() {
        return serverName;
    }

    public String getSpiderX() {
        return spiderX;
    }
}
