package com.mhsenpc.v2raybot.bot.controllers.telegram;

import com.mhsenpc.v2raybot.telegram.types.Update;

public interface ITelegramController {

    void invoke(Update update);
}
