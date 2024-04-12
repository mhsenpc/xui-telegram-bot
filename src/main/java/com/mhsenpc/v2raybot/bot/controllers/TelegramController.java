package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.dto.BuyAccountRequest;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.*;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.methods.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TelegramController {

    @Autowired
    private BuyAccountSelectPlan buyAccountSelectPlan;

    private final HashMap<String, UserStepWithPayload> userStepWithPayload = new HashMap<>();

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/handle")
    public <T extends Executable> T handleRequests(@RequestBody Update update)  {

        System.out.printf(update.toString() + "\n");

        // we need to detect what is current step of user
        String chatId = "";
        String message = "";
        String callbackQueryData = "";
        if (update.getCallbackQuery() != null){
            message = update.getCallbackQuery().getMessage().getText();
            chatId = update.getCallbackQuery().getFrom().getId();
            callbackQueryData = update.getCallbackQuery().getData();
        }
        else if(update.getMessage() != null){
            message = update.getMessage().getText();
            chatId = update.getMessage().getFrom().getId();

        }
        UserStepWithPayload currentStepWithPayload = userStepWithPayload.get(chatId);

        try {
            if(message.equals(Page.BTN_BACK)){
                userStepWithPayload.remove(chatId);

                HomePage homePage = new HomePage();
                homePage.setChatId(chatId);
                return (T) homePage;
            }

            if(currentStepWithPayload == null){

                // user is on main menu. try to find a submenu for them
                if (message.equals(HomePage.BTN_BUY_CONFIG)) {
                    int userId = userRepository.findByChatId(chatId).getUserId();
                    BuyAccountRequest buyAccountRequest = new BuyAccountRequest();
                    buyAccountRequest.setChatId(chatId);
                    buyAccountRequest.setUserId(userId);
                    UserStepWithPayload stepWithPayload = new UserStepWithPayload(UserStep.BUY_SELECT_PLAN, buyAccountRequest);
                    userStepWithPayload.put(chatId, stepWithPayload);

                    buyAccountSelectPlan.setChatId(chatId);
                    return (T) buyAccountSelectPlan;
                }
                else{
                    HomePage homePage = new HomePage();
                    homePage.setChatId(chatId);
                    return (T) homePage;
                }
            }

            BuyAccountRequest currentPayload = (BuyAccountRequest) currentStepWithPayload.getPayload();
            switch (currentStepWithPayload.getUserStep()){
                case BUY_SELECT_PLAN:
                    int planId = Integer.parseInt(callbackQueryData);
                    currentPayload.setPlanId(planId);
                    currentStepWithPayload.setUserStep(UserStep.BUY_PAYMENT_METHOD);
                    userStepWithPayload.put(chatId, currentStepWithPayload);

                    BuyAccountSelectPaymentMethod buyAccountSelectPaymentMethod = new BuyAccountSelectPaymentMethod();
                    buyAccountSelectPaymentMethod.setChatId(chatId);
                    return (T) buyAccountSelectPaymentMethod;

                case BUY_PAYMENT_METHOD:
                    PaymentMethod paymentMethod = PaymentMethod.valueOf(update.getCallbackQuery().getData());
                    currentPayload.setPaymentMethod(paymentMethod);
                    switch (paymentMethod){
                        case TRANSFER_MONEY -> {
                            currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_RECEIPT);
                            WaitForReceipt waitForReceipt = new WaitForReceipt();
                            waitForReceipt.setChatId(chatId);
                            userStepWithPayload.put(chatId, currentStepWithPayload);
                            return (T) waitForReceipt;
                        }
                        case COUPON -> {
                            currentStepWithPayload.setUserStep(UserStep.BUY_WAIT_FOR_COUPON);
                            WaitForCoupon waitForCoupon = new WaitForCoupon();
                            waitForCoupon.setChatId(chatId);
                            userStepWithPayload.put(chatId, currentStepWithPayload);

                            return (T) waitForCoupon;
                        }
                        case WALLET -> {
                            currentStepWithPayload.setUserStep(UserStep.BUY_CONFIRMATION);
                            userStepWithPayload.put(chatId, currentStepWithPayload);

                        }

                    }

                default:
                    HomePage homePage = new HomePage();
                    homePage.setChatId(chatId);
                    return (T) homePage;
            }
        }
        catch (Exception exception){
            SendMessageMethod sendMessageMethod = new SendMessageMethod();
            System.out.printf(exception.getMessage());
            sendMessageMethod.setChatId(update.getMessage().getChat().getId());
            sendMessageMethod.setText(
                    "یگ مشکل فنی به وجود آمده است" + "\n" +
                    exception.getMessage());
            return (T) sendMessageMethod;
        }

    }

}
