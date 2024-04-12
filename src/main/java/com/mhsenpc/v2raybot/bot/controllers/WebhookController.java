package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.telegram.methods.SetWebhookMethodBase;
import com.mhsenpc.v2raybot.telegram.types.SetWebhookResponse;
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

        if(!url.endsWith("/")){
            url += "/";
        }

        SetWebhookMethodBase setWebhookMethod = new SetWebhookMethodBase();
        setWebhookMethod.addQueryParam("url", url + "handle");
        setWebhookMethod.setToken(this.config.getToken());
        SetWebhookResponse response = requestHandler.send(setWebhookMethod, SetWebhookResponse.class);

        return response.toString();
    }
}
