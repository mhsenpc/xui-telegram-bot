package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;
import com.mhsenpc.v2raybot.telegram.dto.Update;
import com.mhsenpc.v2raybot.telegram.interfaces.Requestable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramController {

    @RequestMapping("/handle")
    public <T extends Requestable> T handleRequests(@RequestBody Update update)  {

        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        try {
            System.out.printf(update.toString() + "\n");
            sendMessageRequest.setChatId(update.getMessage().getChat().getId());
            sendMessageRequest.setText(update.getMessage().getText());
            System.out.printf("going to send " + sendMessageRequest + "\n");
        }
        catch (Exception exception){
            System.out.printf(exception.getMessage());
            sendMessageRequest.setChatId(update.getMessage().getChat().getId());
            sendMessageRequest.setText(
                    "یگ مشکل فنی به وجود آمده است" + "\n" +
                    exception.getMessage());
        }
        return (T) sendMessageRequest;
    }
}
