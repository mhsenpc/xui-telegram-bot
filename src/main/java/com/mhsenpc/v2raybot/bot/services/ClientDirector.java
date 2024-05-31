package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Plan;
import com.mhsenpc.v2raybot.bot.services.name.ClientNameProvider;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.v2raybot.xui.services.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientDirector {

    @Autowired
    private ClientBuilder clientBuilder;

    @Autowired
    private ClientNameProvider clientNameProvider;

    @Autowired
    private ConfigurationManager configurationManager;

    public XUIClient build(Order order) throws InboundNotRetrievedException, IOException {

        XuiConfigAdapter configAdapter = new XuiConfigAdapter(configurationManager.getConfig());
        clientBuilder.setXuiConfig(configAdapter);
        clientBuilder.setEmail(clientNameProvider.getName());
        Plan plan = order.getPlan();
        clientBuilder.setTrafficLimitInGB(plan.getTrafficLimit());
        clientBuilder.setConnectionLimit(plan.getConnectionLimit());
        clientBuilder.setExpiryTimeInMonths(plan.getMonths());
        return clientBuilder.build();
    }
}
