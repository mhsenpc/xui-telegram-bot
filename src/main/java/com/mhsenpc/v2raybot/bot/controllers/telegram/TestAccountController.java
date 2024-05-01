package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.services.name.TestNameProvider;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
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

    @Autowired
    private TestNameProvider testNameProvider;

    @Override
    public void invoke(Update update) {

        testClientBuilder.setXuiConfig(getConfig());
        testClientBuilder.setEmail(testNameProvider.getName());
        testClientBuilder.setTrafficInMB(512);
        testClientBuilder.setExpiryNextDay();
        Client client =  testClientBuilder.build();
        if(client == null){
            System.out.println("failed to create a test client");
        }

        this.sendClientDetails(client);
    }

    private void sendClientDetails(Client client) {

        SendMessageMethod sendMessageMethod = new SendMessageMethod();
        sendMessageMethod.setChatId(chatId);
        sendMessageMethod.setText("اکانت تست با موفقیت ساخته شد");
        sendMessageMethod.setToken(this.config.getToken());
        this.requestHandler.send(sendMessageMethod, Message.class);
    }

    private XuiConfig getConfig(){

        return new XuiConfig(this.config.getBaseUrl(), this.config.getUsername(), this.config.getPassword());
    }
}
