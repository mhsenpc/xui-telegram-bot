package com.mhsenpc.v2raybot.xui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClientSettings {
    @JsonProperty("clients")
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
