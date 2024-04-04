package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.BasePage;
import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectPaymentMethod;
import com.mhsenpc.v2raybot.bot.pages.BuyAccountSelectPlan;
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

    private final HashMap<Integer, UserStepWithPayload> userStepWithPayload = new HashMap<>();

    @RequestMapping("/handle")
    public <T extends IRequest> T handleRequests(@RequestBody Update update)  {

        System.out.printf(update.toString() + "\n");

        // we need to detect what is current step of user
        int chatId = update.getMessage().getFrom().getId();
        String message = update.getMessage().getText();
        UserStepWithPayload currentStepWithPayload = userStepWithPayload.get(chatId);

        try {
            if(message.equals(BasePage.BTN_BACK)){
                userStepWithPayload.remove(chatId);

                HomePage homePage = new HomePage();
                homePage.setChatId(chatId);
                return (T) homePage;
            }

            if(currentStepWithPayload == null){

                // user is on main menu. try to find a submenu for them
                switch (update.getMessage().getText()){
                    case HomePage.BTN_BUY_CONFIG:
                        BuyAccountRequest buyAccountRequest = new BuyAccountRequest();
                        buyAccountRequest.setChatId(chatId);
                        buyAccountRequest.setUserId(chatId); //todo: user id in ysers table
                        UserStepWithPayload stepWithPayload = new UserStepWithPayload(UserStep.BUY_SELECT_PLAN, buyAccountRequest);
                        userStepWithPayload.put(chatId, stepWithPayload);

                        return (T) new BuyAccountSelectPlan(chatId);

                    default:
                        HomePage homePage = new HomePage();
                        homePage.setChatId(chatId);
                        return (T) homePage;
                }
            }

            BuyAccountRequest currentPayload = (BuyAccountRequest) currentStepWithPayload.getPayload();
            switch (currentStepWithPayload.getUserStep()){
                case BUY_SELECT_PLAN:
                    int planId = Integer.parseInt(update.getCallbackQuery().getData());
                    currentPayload.setPlanId(planId);
                    currentStepWithPayload.setUserStep(UserStep.BUY_PAYMENT_METHOD);
                    userStepWithPayload.put(chatId, currentStepWithPayload);
                    return (T) new BuyAccountSelectPlan(chatId);

                case BUY_PAYMENT_METHOD:
                    PaymentMethod paymentMethod = PaymentMethod.valueOf(update.getCallbackQuery().getData());
                    currentPayload.setPaymentMethod(paymentMethod);
                    currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_IMAGE);
                    userStepWithPayload.put(chatId, currentStepWithPayload);
                    return (T) new BuyAccountSelectPaymentMethod(chatId);

                default:
                    HomePage homePage = new HomePage();
                    homePage.setChatId(chatId);
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

}
