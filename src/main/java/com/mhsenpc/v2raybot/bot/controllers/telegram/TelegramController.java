package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.bot.controllers.BaseController;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.stereotype.Component;

@Component
public abstract class TelegramController extends BaseController implements ITelegramController {

    protected String chatId;
    protected String message;
    protected UserStepWithPayload currentStepWithPayload;

    @Override
    public abstract void invoke(Update update);

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserStepWithPayload getCurrentStepWithPayload() {
        return currentStepWithPayload;
    }

    public void setCurrentStepWithPayload(UserStepWithPayload currentStepWithPayload) {
        this.currentStepWithPayload = currentStepWithPayload;
    }
}
