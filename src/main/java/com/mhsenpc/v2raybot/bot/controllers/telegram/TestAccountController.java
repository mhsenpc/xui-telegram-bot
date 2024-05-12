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

        XUIClient XUIClient = null;
        try {
            XUIClient = testClientDirector.build();
            this.sendClientDetails(XUIClient);
        } catch (Exception e) {
            System.out.println(e);
            this.sendMessage("متاسفانه در فرآیند ساخت اکانت تست یک مشکل فنی به وجود آمده است");
        }

    }

    private void sendClientDetails(XUIClient XUIClient) {

        this.sendMessage("اکانت تست با موفقیت ساخته شد");

    }
}
