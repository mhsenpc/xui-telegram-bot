package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;

import java.util.HashMap;

public class UserStepService {

    private final HashMap<String, UserStepWithPayload> userStepWithPayload = new HashMap<>();

    public UserStepWithPayload get(String chatId){
        return userStepWithPayload.get(chatId);
    }

    public void set(String chatId, UserStepWithPayload newUserStepWithPayload){
        this.userStepWithPayload.put(chatId, newUserStepWithPayload);
    }

    public void clear(String chatId){

        userStepWithPayload.remove(chatId);
    }
}
