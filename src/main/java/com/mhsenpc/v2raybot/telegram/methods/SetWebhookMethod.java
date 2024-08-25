package com.mhsenpc.v2raybot.telegram.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.InputStream;

public class SetWebhookMethod extends BaseTelegramMethod {

    private String url;
    @JsonIgnore
    private InputStream certificate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getCertificate() {
        return certificate;
    }

    public void setCertificate(InputStream certificate) {
        this.certificate = certificate;
    }

    public SetWebhookMethod() {
        this.setMethod("setwebhook");
    }
}
