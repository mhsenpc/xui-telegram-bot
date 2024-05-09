package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.services.TestClientDirector;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAccountController extends TelegramController{

    @Autowired
    private TestClientDirector testClientDirector;

    @Override
    public void invoke(Update update) {

        XUIClient XUIClient = testClientDirector.build();
        if(XUIClient == null){
            System.out.println("failed to create a test client");
        }

        this.sendClientDetails(XUIClient);
    }

    private void sendClientDetails(XUIClient XUIClient) {

        this.sendMessage("اکانت تست با موفقیت ساخته شد");

    }
}
