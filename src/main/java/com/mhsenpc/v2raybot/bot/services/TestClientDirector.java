package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.services.name.TestNameProvider;
import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.services.TestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestClientDirector {

    public static final int TRAFFIC_IN_MB = 512;

    @Autowired
    private TestClientBuilder testClientBuilder;

    @Autowired
    private TestNameProvider testNameProvider;

    public Client build(){
        XuiConfigAdapter configAdapter = new XuiConfigAdapter(Config.getInstance());
        testClientBuilder.setXuiConfig(configAdapter);
        testClientBuilder.setEmail(testNameProvider.getName());
        testClientBuilder.setTrafficInMB(TRAFFIC_IN_MB);
        testClientBuilder.setExpiryNextDay();
        return testClientBuilder.build();
    }
}
