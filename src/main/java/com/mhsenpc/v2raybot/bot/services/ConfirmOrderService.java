package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmOrderService {

    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageService messageService;

    public void setOrderStatusToConfirmed(){

        order.setStatus(OrderStatus.CONFIRMED.getValue());
        orderRepository.save(order);
    }

    public void sendConfirmationMessageToUser(){

        String message = "تبریک. سفارش شما به شماره " + order.getOrderId() + " تایید شد";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }


    public void sendAccountDetailsTOUser(){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
