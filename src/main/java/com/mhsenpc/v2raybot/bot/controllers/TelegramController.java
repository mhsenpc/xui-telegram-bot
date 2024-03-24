package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectDurationPage;
import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;
import com.mhsenpc.v2raybot.telegram.dto.Update;
import com.mhsenpc.v2raybot.telegram.interfaces.IRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramController {

    @RequestMapping("/handle")
    public <T extends IRequest> T handleRequests(@RequestBody Update update)  {

        try {

            System.out.printf(update.toString() + "\n");

            switch (update.getMessage().getText()){
                case HomePage.BTN_BUY_CONFIG:
                    BuyAccountSelectDurationPage buyAccountSelectDurationPage = new BuyAccountSelectDurationPage();
                    buyAccountSelectDurationPage.setChatId(update.getMessage().getChat().getId());
                    return (T) buyAccountSelectDurationPage;
                case BuyAccountSelectDurationPage.BTN_BACK:
                default:
                    HomePage homePage = new HomePage();
                    homePage.setChatId(update.getMessage().getChat().getId());
                    return (T) homePage;
            }
        }
        catch (Exception exception){
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            System.out.printf(exception.getMessage());
            sendMessageRequest.setChatId(update.getMessage().getChat().getId());
            sendMessageRequest.setText(
                    "یگ مشکل فنی به وجود آمده است" + "\n" +
                    exception.getMessage());
            return (T) sendMessageRequest;
        }
    }

    @RequestMapping("/test")
    public <T extends IRequest> T test() {
        HomePage homePage = new HomePage();
        homePage.setChatId("11");
        System.out.printf("going to send " + homePage + "\n");
        return (T) homePage;
    }
}
