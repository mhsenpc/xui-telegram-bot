package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.controllers.BaseController;
import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MainMenuController extends BaseController implements ITelegramController {

    @Autowired
    private RequestHandler requestHandler;

    @Override
    public void invoke(Update update) {

        // we need to detect what is current step of user
        String chatId = "";
        String message = "";
        String callbackQueryData = "";
        if (update.getCallbackQuery() != null){
            message = Optional.ofNullable(update.getCallbackQuery().getMessage().getText()).orElse(message);
            chatId = update.getCallbackQuery().getFrom().getId();
            callbackQueryData = update.getCallbackQuery().getData();
        }
        else if(update.getMessage() != null){
            message = Optional.ofNullable(update.getMessage().getText()).orElse(message);
            chatId = update.getMessage().getFrom().getId();
        }

        HomePage homePage = new HomePage();
        homePage.setChatId(chatId);
        homePage.setToken(this.config.getToken());

        this.requestHandler.send(homePage, Message.class);
    }
}
