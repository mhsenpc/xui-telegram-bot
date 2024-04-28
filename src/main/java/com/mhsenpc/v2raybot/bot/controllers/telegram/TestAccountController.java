package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.services.TestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAccountController extends TelegramController{

    @Autowired
    private TestClientBuilder testClientBuilder;

    @Override
    public void invoke(Update update) {

        testClientBuilder.setXuiConfig(getConfig());
        testClientBuilder.setEmail("my test 2"); // todo: we need a logic for this
        testClientBuilder.setTrafficInMB(512);
        testClientBuilder.setExpiryNextDay();
        Client client =  testClientBuilder.build();
        if(client == null){
            System.out.println("failed to create a test client");
        }
    }

    private XuiConfig getConfig(){

        return new XuiConfig(this.config.getBaseUrl(), this.config.getUsername(), this.config.getPassword());
    }
}
