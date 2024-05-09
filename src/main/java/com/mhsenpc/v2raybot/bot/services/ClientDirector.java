package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.services.name.ClientNameProvider;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.services.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDirector {

    @Autowired
    private ClientBuilder clientBuilder;

    @Autowired
    private ClientNameProvider clientNameProvider;

    public XUIClient build(Order order){

        XuiConfigAdapter configAdapter = new XuiConfigAdapter(Config.getInstance());
        clientBuilder.setXuiConfig(configAdapter);
        clientBuilder.setEmail(clientNameProvider.getName());
        Plan plan = order.getPlan();
        clientBuilder.setTrafficLimitInGB(plan.getTrafficLimit());
        clientBuilder.setConnectionLimit(plan.getConnectionLimit());
        clientBuilder.setExpiryTimeInMonths(plan.getMonths());
        return clientBuilder.build();
    }
}
