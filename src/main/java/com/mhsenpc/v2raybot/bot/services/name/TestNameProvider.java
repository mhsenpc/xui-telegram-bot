package com.mhsenpc.v2raybot.bot.services.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestNameProvider implements INameProvider {

    @Autowired
    private RandomString randomString;

    private static final String PREFIX = "test_";
    private static final int length = 6;

    @Override
    public String getName() {
        return PREFIX + randomString.generate(length);
    }
}
