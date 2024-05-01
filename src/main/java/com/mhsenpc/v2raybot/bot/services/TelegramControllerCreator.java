package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.controllers.telegram.*;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.pages.BasePage;
import com.mhsenpc.v2raybot.bot.pages.HomePage;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelegramControllerCreator {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private MainMenuController mainMenuController;

    @Autowired
    private BuyController buyController;

    @Autowired
    private ViewOrdersController viewOrdersController;

    @Autowired
    private TestAccountController testAccountController;

    protected String chatId;
    protected String message;
    protected UserStepWithPayload currentStepWithPayload;

    public TelegramController getController(Update update){

        if (update.getCallbackQuery() != null){
            message = Optional.ofNullable(update.getCallbackQuery().getMessage().getText()).orElse(message);
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        else if(update.getMessage() != null){
            message = Optional.ofNullable(update.getMessage().getText()).orElse(message);
            chatId = update.getMessage().getFrom().getId();

        }
        currentStepWithPayload = userStepService.get(chatId);

        TelegramController telegramController = createController(update);
        telegramController.setMessage(message);
        telegramController.setChatId(chatId);
        telegramController.setCurrentStepWithPayload(currentStepWithPayload);
        return telegramController;
    }

    protected TelegramController createController(Update update){

        switch (message){
            case HomePage.BTN_BUY_CONFIG->{
                return buyController;
            }
            case HomePage.BTN_VIEW_ORDERS -> {
                return viewOrdersController;
            }
            case HomePage.BTN_TEST_ACCOUNT -> {
                return testAccountController;
            }
            case BasePage.BTN_BACK->{
                return mainMenuController;
            }
        }

        if(currentStepWithPayload != null){
            return switch (currentStepWithPayload.getUserStep()) {
                case BUY_SELECT_PLAN, BUY_PAYMENT_METHOD, BUY_WAIT_FOR_RECEIPT -> buyController;
                case ADMIN_VIEW_ORDERS -> viewOrdersController;
                default -> mainMenuController;
            };
        }

        return mainMenuController;
    }
}
