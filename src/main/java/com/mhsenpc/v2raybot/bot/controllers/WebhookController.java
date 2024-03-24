package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.telegram.dto.MessageResponse;
import com.mhsenpc.v2raybot.telegram.dto.SetWebhookRequest;
import com.mhsenpc.v2raybot.telegram.dto.SetWebhookResponse;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    @Autowired
    RequestHandler requestHandler;

    @Value("${api.token}")
    private String apiToken;

    @PostMapping("/setWebhook")
    public SetWebhookResponse setWebhook(@RequestParam String url){
        SetWebhookRequest setWebhookRequest = new SetWebhookRequest();
        setWebhookRequest.addQueryParam("url", url + "/handle");
        setWebhookRequest.setToken(apiToken);
        return requestHandler.send(setWebhookRequest, SetWebhookResponse.class);
    }
}
