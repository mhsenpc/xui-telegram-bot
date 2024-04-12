package com.mhsenpc.v2raybot.telegram.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetWebhookResponse implements IResponse {
    private boolean ok;
    private boolean result;
    private String description;

    @JsonProperty("error_code")
    private int errorCode;

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "SetWebhookResponse{" +
                "ok=" + ok +
                ", result=" + result +
                ", description='" + description + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
