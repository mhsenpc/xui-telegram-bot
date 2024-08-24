package com.mhsenpc.v2raybot.telegram.methods;

public class SetWebhookMethodBase extends BaseTelegramMethod {

    private String certificate;

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public SetWebhookMethodBase() {
        this.setMethod("setwebhook");
    }
}
