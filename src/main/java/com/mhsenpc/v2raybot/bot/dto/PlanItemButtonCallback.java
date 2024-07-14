package com.mhsenpc.v2raybot.bot.dto;

import com.mhsenpc.v2raybot.bot.enums.PlanCommandType;

public class PlanItemButtonCallback {

    private int planId;
    private PlanCommandType commandType;

    public PlanItemButtonCallback() {
    }

    public PlanItemButtonCallback(int planId, PlanCommandType commandType) {
        this.planId = planId;
        this.commandType = commandType;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public PlanCommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(PlanCommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return "PlanItemButtonCallback{" +
                "planId=" + planId +
                ", commandType=" + commandType +
                '}';
    }
}
