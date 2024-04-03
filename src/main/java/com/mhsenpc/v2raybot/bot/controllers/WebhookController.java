package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.telegram.dto.SetWebhookRequest;
import com.mhsenpc.v2raybot.telegram.dto.SetWebhookResponse;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController extends BaseController {
    @Autowired
    RequestHandler requestHandler;

    @PostMapping("/setWebhook")
    public String setWebhook(@RequestParam String url){
        SetWebhookRequest setWebhookRequest = new SetWebhookRequest();
        setWebhookRequest.addQueryParam("url", url + "handle");
        setWebhookRequest.setToken(this.config.getToken());
        SetWebhookResponse response = requestHandler.send(setWebhookRequest, SetWebhookResponse.class);

        return response.toString();
    }
}
