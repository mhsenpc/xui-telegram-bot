package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.XUIConfigBuilder;
import com.mhsenpc.v2raybot.bot.services.name.TestNameProvider;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.v2raybot.xui.services.TestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TestClientDirector {

    public static final int TRAFFIC_IN_MB = 512;

    @Autowired
    private TestClientBuilder testClientBuilder;

    @Autowired
    private TestNameProvider testNameProvider;

    @Autowired
    private XUIConfigBuilder xuiConfigBuilder;

    public XUIClient build() throws InboundNotRetrievedException, IOException {
        testClientBuilder.setXuiConfig(xuiConfigBuilder.build());
        testClientBuilder.setEmail(testNameProvider.getName());
        testClientBuilder.setTrafficInMB(TRAFFIC_IN_MB);
        testClientBuilder.setExpiryNextDay();
        return testClientBuilder.build();
    }
}
