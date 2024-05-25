package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.services.HomePageFactory;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController extends TelegramController {

    @Autowired
    private HomePageFactory homePageFactory;

    @Override
    public void invoke(Update update) {

        SendMessageMethod homePage = homePageFactory.getHomePage(update.getMessage().getFrom());
        homePage.setChatId(chatId);

        this.requestHandler.send(homePage, Message.class);
    }
}
