package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.dto.OrderItemButtonCallback;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.enums.OrderCommandType;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.services.OrderItemButtonCallbackSerializer;
import com.mhsenpc.v2raybot.bot.services.OrderService;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandleOrdersController extends TelegramController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserStepService userStepService;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            try {
                callbackQueryData = update.getCallbackQuery().getData();

                OrderItemButtonCallback response = OrderItemButtonCallbackSerializer.deserialize(callbackQueryData);
                this.handleOrderConfirmationCallback(response);

            } catch (Exception e) {
                this.sendMessage("متاسفانه مشکلی در دریافت فرمان شما پیش آمده است");
                System.out.println(e);
            }

            userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_ORDERS));

        }

    }

    private void handleOrderConfirmationCallback(OrderItemButtonCallback response) {

        int orderId = response.getOrderId();

        if(response.getCommandType() == OrderCommandType.ACCEPT) {
            acceptOrder(orderId);
        }
        else if(response.getCommandType() == OrderCommandType.DENY){
            denyOrder(orderId);
        }

    }

    private void denyOrder(int orderId) {
        try{
            orderService.rejectOrder(orderId);
            this.sendMessage("این سفارش رد شد و به زودی به اطلاع کاربر می رسد");
        }
        catch (Exception exception) {
            this.sendMessage("عملیات رد کردن سفارش با مشکل فنی مواجه شد");
        }
    }

    private void acceptOrder(int orderId) {
        try {
            orderService.acceptOrder(orderId);
            this.sendMessage("این سفارش با موفقیت تایید شد. کاربر به زودی اطلاعات اکانت را دریافت خواهد کرد");

        } catch (Exception exception) {
            this.sendMessage("متاسفانه تایید این سفارش با مشکل مواجه شد. لطفا مجدد امتحان کنید");
        }
    }
}
