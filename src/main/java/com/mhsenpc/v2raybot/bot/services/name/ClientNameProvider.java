package com.mhsenpc.v2raybot.bot.services.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientNameProvider implements INameProvider {

    @Autowired
    private RandomString randomString;

    @Override
    public String getName() {
        return "user_" + randomString.generate(5);
    }
}
