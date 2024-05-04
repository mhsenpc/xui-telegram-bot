package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.enums.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public abstract class ClientBuilder {

    protected Client client = new Client();
    protected XuiConfig xuiConfig;

    @Autowired
    protected ClientManager clientManager;

    public Client getClient(){
        return this.client;
    }

    public ClientBuilder setTrafficInMB(double trafficInMB) {

        long trafficInBytes = (long) ((trafficInMB * 1024) * 1024);
        client.setTraffic(trafficInBytes);
        return this;
    }

    public ClientBuilder setEmail(String email) {

        client.setEmail(email);
        return this;
    }

    public ClientBuilder setXuiConfig(XuiConfig xuiConfig){

        this.xuiConfig = xuiConfig;
        return this;
    }

    public ClientBuilder setConnectionLimit(int connectionLimit){
        this.client.setLimitIp(connectionLimit);
        return this;
    }

    public ClientBuilder setTrafficLimitInGB(long trafficLimitInGB){

        long trafficInBytes = (((trafficLimitInGB * 1024) * 1024) * 1024);
        client.setTraffic(trafficInBytes);
        return this;
    }

    public ClientBuilder setExpiryTimeInMonths(int months) {

        LocalDateTime next3Months = LocalDateTime.now().plusMonths(months);
        long timestamp = next3Months.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();

        client.setExpiryTime(timestamp);
        return this;
    }

    public Client build(){

        client.setId(UUID.randomUUID().toString());
        client.setFlow(Flow.XTLS_RPRX_VISION);
        clientManager.setXuiConfig(this.xuiConfig);
        clientManager.setInboundId("2"); //todo: this should be dynamic

        CreateUserResponse createUserResponse = null;
        try {
            createUserResponse = clientManager.save(client);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (createUserResponse != null && createUserResponse.isSuccess()) {
            return client;
        }
        return null;
    }


}
