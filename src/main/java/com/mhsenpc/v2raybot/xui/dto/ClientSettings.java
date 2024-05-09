package com.mhsenpc.v2raybot.xui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClientSettings {
    @JsonProperty("clients")
    private List<XUIClient> XUIClients;

    public List<XUIClient> getClients() {
        return XUIClients;
    }

    public void setClients(List<XUIClient> XUIClients) {
        this.XUIClients = XUIClients;
    }
}
