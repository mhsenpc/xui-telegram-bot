package com.xui.telegram.bot.dto;

public class Settings {
    private String publicKey;
    private String fingerprint;
    private String serverName;
    private String spiderX;

    public Settings() {
    }

    public Settings(String publicKey, String fingerprint, String serverName, String spiderX) {
        this.publicKey = publicKey;
        this.fingerprint = fingerprint;
        this.serverName = serverName;
        this.spiderX = spiderX;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSpiderX() {
        return spiderX;
    }

    public void setSpiderX(String spiderX) {
        this.spiderX = spiderX;
    }
}
