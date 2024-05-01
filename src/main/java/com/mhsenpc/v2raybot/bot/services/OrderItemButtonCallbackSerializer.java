package com.mhsenpc.v2raybot.bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.v2raybot.bot.dto.OrderItemButtonCallback;

public class OrderItemButtonCallbackSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(OrderItemButtonCallback obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static OrderItemButtonCallback deserialize(String json) throws Exception {
        return objectMapper.readValue(json, OrderItemButtonCallback.class);
    }
}