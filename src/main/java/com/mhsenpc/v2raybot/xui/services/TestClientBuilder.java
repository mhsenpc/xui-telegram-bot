package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.enums.Flow;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TestClientBuilder extends ClientBuilder {

    public Client build(){

        client.setId(UUID.randomUUID().toString());
        client.setFlow(Flow.XTLS_RPRX_VISION);
        client.setLimitIp(1);
        clientManager.setXuiConfig(this.xuiConfig);
        clientManager.setInboundId("2");

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

    public Client getClient(){
        return this.client;
    }

    public void setExpiryNextDay(){

        //TODO: fix this. doesn't correctly set timestamp
        LocalDate nextDay = LocalDate.now().plusDays(1);
        long nextDayEpoch = nextDay.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        this.client.setExpiryTime(nextDayEpoch);
    }

}
