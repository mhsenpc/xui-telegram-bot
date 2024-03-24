package com.mhsenpc.v2raybot.telegram.dto;

import com.mhsenpc.v2raybot.telegram.interfaces.IResponse;

public class SetWebhookResponse implements IResponse {
    private boolean ok;
    private boolean result;
    private String description;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
