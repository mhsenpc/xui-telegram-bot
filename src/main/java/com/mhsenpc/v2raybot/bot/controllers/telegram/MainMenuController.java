package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController extends TelegramController {

    @Autowired
    private RequestHandler requestHandler;

    @Override
    public void invoke(Update update) {

        HomePage homePage = new HomePage();
        homePage.setChatId(chatId);
        homePage.setToken(this.config.getToken());

        this.requestHandler.send(homePage, Message.class);
    }
}
