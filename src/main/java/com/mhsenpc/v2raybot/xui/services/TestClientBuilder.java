package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class TestClientBuilder extends ClientBuilder {

    public void setExpiryNextDay(){

        //TODO: fix this. doesn't correctly set timestamp
        LocalDate nextDay = LocalDate.now().plusDays(1);
        long nextDayEpoch = nextDay.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        this.XUIClient.setExpiryTime(nextDayEpoch);
    }

    @Override
    public XUIClient build() throws InboundNotRetrievedException, IOException {
        this.setConnectionLimit(1);
        return super.build();
    }
}
