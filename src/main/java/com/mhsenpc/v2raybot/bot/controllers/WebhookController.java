package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
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

    @Autowired
    private ConfigurationManager configurationManager;

    @PostMapping("/setWebhook")
    public String setWebhook(@RequestParam String url){

        Config config = configurationManager.getConfig();

        if(!url.endsWith("/")){
            url += "/";
        }

        SetWebhookMethodBase setWebhookMethod = new SetWebhookMethodBase();
        setWebhookMethod.addQueryParam("url", url + "handle");
        setWebhookMethod.setToken(config.getToken());

        try {
            SetWebhookResponse response = requestHandler.send(setWebhookMethod, SetWebhookResponse.class);
            return "تبریک. وب هوک با موفقیت ثبت شد";
        }
        catch (Exception exception){
            return "متاسفانه در تنظیم کردن وب هوک تلگرام مشکلی پیش آمد" + System.lineSeparator() + exception.getMessage()
                    + "Token: " + config.getToken()
                    + "Url: " + url;
        }
    }
}
