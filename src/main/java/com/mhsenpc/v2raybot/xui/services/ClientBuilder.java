package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientBuilder {

    protected Client client = new Client();
    protected XuiConfig xuiConfig;

    @Autowired
    protected ClientManager clientManager;

    public void setTrafficInMB(double trafficInMB) {
        client.setTrafficInMB(trafficInMB);
    }

    public void setEmail(String email) {
        client.setEmail(email);
    }

    public void setXuiConfig(XuiConfig xuiConfig){
        this.xuiConfig = xuiConfig;
    }
}
