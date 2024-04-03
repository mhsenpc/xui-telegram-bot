package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectDurationPage;
import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectTraffic;
import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;
import com.mhsenpc.v2raybot.telegram.dto.Update;
import com.mhsenpc.v2raybot.telegram.interfaces.IRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TelegramController {

    private HashMap<Integer, UserStepWithPayload> userStepWithPayload = new HashMap<>();

    @RequestMapping("/handle")
    public <T extends IRequest> T handleRequests(@RequestBody Update update)  {

        System.out.printf(update.toString() + "\n");

        // we need to detect what is current step of user
        int chatId = update.getMessage().getFrom().getId();
        String message = update.getMessage().getText();
        UserStepWithPayload currentStepWithPayload = userStepWithPayload.get(chatId);

        try {
            if(message.equals(BuyAccountSelectDurationPage.BTN_BACK)){
                userStepWithPayload.remove(chatId);

                HomePage homePage = new HomePage();
                homePage.setChatId(chatId);
                return (T) homePage;
            }

            if(currentStepWithPayload == null){

                // user is on main menu. try to find a submenu for them
                switch (update.getMessage().getText()){
                    case HomePage.BTN_BUY_CONFIG:
                        this.userStepWithPayload.put(chatId, new UserStepWithPayload(UserStep.BUY_MONTH));

                        BuyAccountSelectDurationPage buyAccountSelectDurationPage = new BuyAccountSelectDurationPage();
                        buyAccountSelectDurationPage.setChatId(chatId);
                        return (T) buyAccountSelectDurationPage;

                    default:
                        HomePage homePage = new HomePage();
                        homePage.setChatId(chatId);
                        return (T) homePage;
                }
            }

            switch (currentStepWithPayload.getUserStep()){
                case BUY_MONTH:
                    // we expect month number from user
                    BuyAccountRequest buyAccountRequest = new BuyAccountRequest();
                    int months = 0;
                    try {
                        months = Integer.parseInt(update.getMessage().getText());
                    }
                    catch (NumberFormatException numberFormatException){

                    }
                    if(months > 0) {
                        buyAccountRequest.setMonths(months);
                        currentStepWithPayload.setPayload(buyAccountRequest);
                        currentStepWithPayload.setUserStep(UserStep.BUY_TRAFFIC);
                        userStepWithPayload.put(chatId, currentStepWithPayload);

                        BuyAccountSelectTraffic buyAccountSelectTrafficPage = new BuyAccountSelectTraffic();
                        buyAccountSelectTrafficPage.setChatId(chatId);
                        return (T) buyAccountSelectTrafficPage;
                    }
                    else {
                        HomePage homePage = new HomePage();
                        homePage.setChatId(chatId);
                        return (T) homePage;
                    }


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

        HomePage homePage = new HomePage();
        homePage.setChatId(chatId);
        return (T) homePage;

    }

}
