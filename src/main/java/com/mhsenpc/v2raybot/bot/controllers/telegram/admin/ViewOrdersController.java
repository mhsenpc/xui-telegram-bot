package com.mhsenpc.v2raybot.bot.controllers.telegram.admin;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.OrderItemButtonCallback;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.enums.OrderCommandType;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.pages.admin.orders.ViewOrdersPage;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.services.OrderFormatter;
import com.mhsenpc.v2raybot.bot.services.OrderItemButtonCallbackSerializer;
import com.mhsenpc.v2raybot.bot.services.OrderService;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewOrdersController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderFormatter orderFormatter;

    @Override
    public void invoke(Update update) throws Exception {

        userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_ORDERS));


        switch (message){
            case ViewOrdersPage.BTN_PENDING_ORDERS -> {
                List<Order> orders = orderRepository.findByStatusAndPhotosIsNotEmpty(OrderStatus.PENDING.getValue());
                if(orders.isEmpty()){
                    this.sendMessage("هیچ سفارش در انتظار تایید وجود ندارد");
                }
                for (Order tempOrder: orders){
                    SendMessageMethod orderItemMessage = new SendMessageMethod();
                    orderItemMessage.setChatId(chatId);
                    orderItemMessage.setText(orderFormatter.getFormattedOrder(tempOrder));

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    String denyOrderCallbackData = OrderItemButtonCallbackSerializer.serialize( new OrderItemButtonCallback(tempOrder.getOrderId(), OrderCommandType.DENY));
                    String acceptOrderCallbackData = OrderItemButtonCallbackSerializer.serialize( new OrderItemButtonCallback(tempOrder.getOrderId(), OrderCommandType.ACCEPT));

                    inlineKeyboardMarkup.addRow(
                            new InlineKeyboardButton("رد", denyOrderCallbackData ),
                            new InlineKeyboardButton("تایید", acceptOrderCallbackData)
                    );

                    orderItemMessage.setReplyMarkup(inlineKeyboardMarkup);

                    requestHandler.send(orderItemMessage, Message.class);
                }

                userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_WAITING_FOR_ORDER_APPROVAL));
            }
            default -> {
                ViewOrdersPage viewOrdersPage = new ViewOrdersPage();
                viewOrdersPage.setText(orderService.getReport());
                viewOrdersPage.setChatId(chatId);

                this.requestHandler.send(viewOrdersPage, Message.class);
            }
        }
    }
}
