package com.mhsenpc.v2raybot.bot.dto;

import com.mhsenpc.v2raybot.bot.enums.UserStep;

public class UserStepWithPayload {
    private UserStep userStep;
    private Object payload;

    public UserStepWithPayload() {
    }

    public UserStepWithPayload(UserStep userStep) {
        this.userStep = userStep;
    }

    public UserStepWithPayload(UserStep userStep, Object payload) {
        this.userStep = userStep;
        this.payload = payload;
    }

    public UserStep getUserStep() {
        return userStep;
    }

    public void setUserStep(UserStep userStep) {
        this.userStep = userStep;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "UserStepWithPayload{" +
                "userStep=" + userStep +
                ", payload=" + payload +
                '}';
    }
}
