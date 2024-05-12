package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.enums.Flow;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public abstract class ClientBuilder {

    protected XUIClient XUIClient = new XUIClient();
    protected XuiConfig xuiConfig;

    @Autowired
    protected ClientManager clientManager;

    public XUIClient getClient(){
        return this.XUIClient;
    }

    public ClientBuilder setTrafficInMB(double trafficInMB) {

        long trafficInBytes = (long) ((trafficInMB * 1024) * 1024);
        XUIClient.setTraffic(trafficInBytes);
        return this;
    }

    public ClientBuilder setEmail(String email) {

        XUIClient.setEmail(email);
        return this;
    }

    public ClientBuilder setXuiConfig(XuiConfig xuiConfig){

        this.xuiConfig = xuiConfig;
        return this;
    }

    public ClientBuilder setConnectionLimit(int connectionLimit){
        this.XUIClient.setLimitIp(connectionLimit);
        return this;
    }

    public ClientBuilder setTrafficLimitInGB(long trafficLimitInGB){

        long trafficInBytes = (((trafficLimitInGB * 1024) * 1024) * 1024);
        XUIClient.setTraffic(trafficInBytes);
        return this;
    }

    public ClientBuilder setExpiryTimeInMonths(int months) {

        LocalDateTime next3Months = LocalDateTime.now().plusMonths(months);
        long timestamp = next3Months.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();

        XUIClient.setExpiryTime(timestamp);
        return this;
    }

    public XUIClient build() throws InboundNotRetrievedException, IOException {

        XUIClient.setId(UUID.randomUUID().toString());
        XUIClient.setFlow(Flow.XTLS_RPRX_VISION);
        clientManager.setXuiConfig(this.xuiConfig);

        CreateUserResponse createUserResponse = clientManager.save(XUIClient);

        if (createUserResponse != null && createUserResponse.isSuccess()) {
            return XUIClient;
        }
        return null;
    }


}
