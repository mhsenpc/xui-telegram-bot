package com.mhsenpc.v2raybot.bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.v2raybot.bot.dto.PlanItemButtonCallback;

public class PlanItemButtonCallbackSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(PlanItemButtonCallback obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static PlanItemButtonCallback deserialize(String json) throws Exception {
        return objectMapper.readValue(json, PlanItemButtonCallback.class);
    }
}