package com.mhsenpc.v2raybot.bot.dto;

import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;

public class BuyAccountRequest {
    private int userId;
    private int chatId;
    private int planId;
    private PaymentMethod paymentMethod;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "BuyAccountRequest{" +
                "userId=" + userId +
                ", chatId=" + chatId +
                ", planId=" + planId +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
