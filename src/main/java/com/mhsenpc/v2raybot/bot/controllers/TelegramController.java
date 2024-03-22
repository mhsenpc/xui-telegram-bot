package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.telegram.dto.MessageResponse;
import com.mhsenpc.v2raybot.telegram.dto.SendMessageRequest;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramController {
    @RequestMapping("/handle")
    public String home(@RequestBody MessageResponse messageResponse)  {

        RequestHandler requestHandler =new RequestHandler();
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        return "ok";
    }
}
