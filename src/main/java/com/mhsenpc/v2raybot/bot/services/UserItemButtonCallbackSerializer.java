package com.mhsenpc.v2raybot.bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.v2raybot.bot.dto.UserItemButtonCallback;

public class UserItemButtonCallbackSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(UserItemButtonCallback obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static UserItemButtonCallback deserialize(String json) throws Exception {
        return objectMapper.readValue(json, UserItemButtonCallback.class);
    }
}