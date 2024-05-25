package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private RequestHandler requestHandler;

    public void send(String chatId, String message){

        SendMessageMethod sendMessageMethod = new SendMessageMethod();
        sendMessageMethod.setChatId(chatId);
        sendMessageMethod.setText(message);
        this.requestHandler.send(sendMessageMethod, Message.class);
    }
}
