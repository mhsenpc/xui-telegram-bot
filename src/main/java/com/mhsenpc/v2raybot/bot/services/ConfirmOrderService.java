package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.config.XUIConfigBuilder;
import com.mhsenpc.v2raybot.bot.entity.Client;
import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Transaction;
import com.mhsenpc.v2raybot.bot.enums.OrderStatus;
import com.mhsenpc.v2raybot.bot.repository.ClientRepository;
import com.mhsenpc.v2raybot.bot.repository.OrderRepository;
import com.mhsenpc.v2raybot.bot.repository.TransactionRepository;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.v2raybot.xui.services.VPNConfigBuilder;
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

    @Autowired
    private VPNConfigBuilder vpnConfigBuilder;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private QRCodeService qrCodeService;

    public void confirm(Order order) throws InboundNotRetrievedException, IOException {

        XUIClient xuiClient = clientDirector.build(order);
        String vpnConfig = this.vpnConfigBuilder
                .setClient(xuiClient)
                .setXUIConfig(XUIConfigBuilder.build())
                .build();
        setOrderStatusToConfirmed(order);
        sendConfirmationMessageToUser(order);
        sendAccountDetailsToUser(order, vpnConfig);
        createTransaction(order);
        storeClient(order, xuiClient, vpnConfig);
    }

    private void storeClient(Order order, XUIClient xuiClient, String vpnConfig) {

        Client client = new Client();
        client.setName(xuiClient.getEmail());
        client.setUrl(vpnConfig);
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

        String message = "تبریک. سفارش شما برای ساخت اکانت  " + order.getPlan().getMonths()  + " ماهه با حجم "  + order.getPlan().getTrafficLimit() + "گیگ تایید شد";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }

    private void sendAccountDetailsToUser(Order order, String vpnConfig){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید" + System.lineSeparator() ;
        String receiverChatId = order.getUser().getChatId();

        String qrCodeUrl = qrCodeService.getQRCodeUrl(vpnConfig);
        messageService.send(receiverChatId, message);

        messageService.sendPhoto(receiverChatId, qrCodeUrl, vpnConfig);
    }
}
