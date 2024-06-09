package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.services.TelegramControllerCreator;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.services.RequestHandler;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TelegramRequestController {

    @Autowired
    private TelegramControllerCreator telegramControllerCreator;

    @Autowired
    private RequestHandler requestHandler;

    @RequestMapping("/handle")
    public void handleRequests(@RequestBody Update update){
        try {

            TelegramController controller = this.telegramControllerCreator.getController(update);
            controller.invoke(update);
            log.info(controller + " invoked");
        }
        catch (Exception exception) {
            SendMessageMethod sendMessageMethod = new SendMessageMethod();
            log.error(exception.toString());
            sendMessageMethod.setChatId(update.getMessage().getChat().getId());
            sendMessageMethod.setText(
                    "یگ مشکل فنی به وجود آمده است" + "\n" +
                            exception.getMessage());
            this.requestHandler.send(sendMessageMethod, Message.class);
        }
    }

}
