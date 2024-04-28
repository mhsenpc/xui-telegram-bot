package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.services.TelegramControllerCreator;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramRequestController {

    @Autowired
    private TelegramControllerCreator telegramControllerCreator;

    @Autowired
    private RequestHandler requestHandler;

    protected Config config;

    public TelegramRequestController() {

        ConfigurationManager configurationManager = new ConfigurationManager();
        this.config = configurationManager.getConfig();
    }

    @RequestMapping("/handle")
    public void handleRequests(@RequestBody Update update){
        try {

            TelegramController controller = this.telegramControllerCreator.getController(update);
            controller.invoke(update);
            System.out.println(controller + " invoked");
        }
        catch (Exception exception) {
            SendMessageMethod sendMessageMethod = new SendMessageMethod();
            System.out.printf(exception.toString());
            sendMessageMethod.setChatId(update.getMessage().getChat().getId());
            sendMessageMethod.setText(
                    "یگ مشکل فنی به وجود آمده است" + "\n" +
                            exception.getMessage());
            sendMessageMethod.setToken(this.config.getToken());
            this.requestHandler.send(sendMessageMethod, Message.class);
        }
    }

}
