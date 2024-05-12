package com.mhsenpc.v2raybot.xui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InboundsListResponse {
    private boolean success;
    private String msg;
    @JsonProperty("obj")
    private List<Inbound> inbounds;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Inbound> getInbounds() {
        return inbounds;
    }

    public void setInbounds(List<Inbound> inbounds) {
        this.inbounds = inbounds;
    }
}