package com.mhsenpc.v2raybot.bot.controllers.telegram;

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

@Component
public class ViewOrdersController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RequestHandler requestHandler;

    @Override
    public void invoke(Update update) {

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
