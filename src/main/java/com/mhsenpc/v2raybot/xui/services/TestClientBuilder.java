package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class TestClientBuilder extends ClientBuilder {

    public void setExpiryNextDay(){

        LocalDateTime nextDayEpoch = LocalDateTime.now().plusDays(1);
        long timestamp = nextDayEpoch.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();

        this.XUIClient.setExpiryTime(timestamp);
    }

    @Override
    public XUIClient build() throws InboundNotRetrievedException, IOException {
        this.setConnectionLimit(1);
        return super.build();
    }
}
