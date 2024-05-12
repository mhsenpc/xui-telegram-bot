package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Client;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Transaction;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.repository.ClientRepository;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.TransactionRepository;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class ConfirmOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ClientDirector clientDirector;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void confirm(Order order) throws InboundNotRetrievedException, IOException {

        XUIClient XUIClient = clientDirector.build(order);
        setOrderStatusToConfirmed(order);
        sendConfirmationMessageToUser(order);
        sendAccountDetailsToUser(order, XUIClient);
        createTransaction(order);
        storeClient(order, XUIClient);
    }

    private void storeClient(Order order, XUIClient xuiClient) {

        Client client = new Client();
        client.setName(xuiClient.getEmail());
        client.setUrl(xuiClient.getConfig());
        client.setOrder(order);
        client.setUser(order.getUser());
        client.setCreatedAt(new Date());
        client.setUuid(xuiClient.getId());
        client.setValidUntil(new Date(xuiClient.getExpiryTime()));
        clientRepository.save(client);
    }

    private void createTransaction(Order order) {

        Transaction transaction = new Transaction();
        transaction.setUser(order.getUser());
        transaction.setAmount(order.getPlan().getPrice());
        transaction.setOrder(order);
        transaction.setCreatedAt(new Date());
        transactionRepository.save(transaction);
    }

    private void setOrderStatusToConfirmed(Order order){

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    private void sendConfirmationMessageToUser(Order order){

        String message = "تبریک. سفارش شما به شماره " + order.getOrderId() + " تایید شد";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }


    private void sendAccountDetailsToUser(Order order, XUIClient XUIClient){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید" + XUIClient.getConfig();
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }
}
