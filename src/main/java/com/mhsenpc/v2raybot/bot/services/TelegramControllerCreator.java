package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.controllers.telegram.*;
import com.mhsenpc.v2raybot.bot.controllers.telegram.admin.CreateConfigController;
import com.mhsenpc.v2raybot.bot.controllers.telegram.admin.HandleOrdersController;
import com.mhsenpc.v2raybot.bot.controllers.telegram.admin.ViewOrdersController;
import com.mhsenpc.v2raybot.bot.controllers.telegram.admin.ViewUsersController;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.pages.BasePage;
import com.mhsenpc.v2raybot.bot.pages.UserHomePage;
import com.mhsenpc.v2raybot.bot.pages.admin.AdminHomePage;
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

    @Autowired
    private HandleOrdersController handleOrdersController;

    @Autowired
    private MyConfigsController myConfigsController;

    @Autowired
    private CreateConfigController createConfigController;

    @Autowired
    private ViewUsersController viewUsersController;

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
            case UserHomePage.BTN_BUY_CONFIG->{
                return buyController;
            }
            case AdminHomePage.BTN_VIEW_ORDERS -> {
                return viewOrdersController;
            }
            case UserHomePage.BTN_TEST_ACCOUNT -> {
                return testAccountController;
            }
            case BasePage.BTN_BACK->{
                return mainMenuController;
            }
            case UserHomePage.BTN_MY_CONFIGS -> {
                return myConfigsController;
            }
            case AdminHomePage.BTN_CREATE_CONFIG -> {
                return createConfigController;
            }
        }

        if(currentStepWithPayload != null){
            return switch (currentStepWithPayload.getUserStep()) {
                case BUY_SELECT_PLAN, BUY_PAYMENT_METHOD, BUY_WAIT_FOR_RECEIPT -> buyController;
                case ADMIN_VIEW_ORDERS -> viewOrdersController;
                case ADMIN_VIEW_USERS -> viewUsersController;
                case ADMIN_WAITING_FOR_ORDER_APPROVAL -> handleOrdersController;
                case ADMIN_SELECT_PLAN -> createConfigController;
                default -> mainMenuController;
            };
        }

        return mainMenuController;
    }
}
