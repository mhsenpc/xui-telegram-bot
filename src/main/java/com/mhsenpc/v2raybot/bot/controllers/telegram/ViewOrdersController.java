package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.controllers.BaseController;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.admin.orders.ViewOrdersPage;
import com.mhsenpc.v2raybot.bot.services.OrderService;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewOrdersController extends BaseController implements ITelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private OrderService orderService;

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

        userStepService.set(chatId, new UserStepWithPayload(UserStep.VIEW_ORDERS));

        switch (message){
            case ViewOrdersPage.BTN_PENDING_ORDERS_WITH_PHOTO -> {


            }
            default -> {
                ViewOrdersPage viewOrdersPage = new ViewOrdersPage();
                viewOrdersPage.setText(orderService.getReport());
                viewOrdersPage.setChatId(chatId);
                viewOrdersPage.setToken(this.config.getToken());

                this.requestHandler.send(viewOrdersPage, Message.class);
            }
        }
    }
}
