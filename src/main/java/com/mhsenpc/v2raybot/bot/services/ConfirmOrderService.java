package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Transaction;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.TransactionRepository;
import com.mhsenpc.v2raybot.xui.dto.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfirmOrderService {

    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ClientDirector clientDirector;

    @Autowired
    private TransactionRepository transactionRepository;

    public void confirm(Order order){

        setOrder(order);
        setOrderStatusToConfirmed();
        sendConfirmationMessageToUser();
        Client client = clientDirector.build(order);
        sendAccountDetailsToUser(client);
        createTransaction(order);
    }

    private void createTransaction(Order order) {

        Transaction transaction = new Transaction();
        transaction.setUser(order.getUser());
        transaction.setAmount(order.getPlan().getPrice());
        transaction.setOrder(order);
        transaction.setCreatedAt(new Date());
        transactionRepository.save(transaction);
    }

    private void setOrderStatusToConfirmed(){

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    private void sendConfirmationMessageToUser(){

        String message = "تبریک. سفارش شما به شماره " + order.getOrderId() + " تایید شد";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }


    private void sendAccountDetailsToUser(Client client){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید" + client.getId();
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }

    private void setOrder(Order order) {
        this.order = order;
    }
}
