package com.mhsenpc.v2raybot.bot.dto;

import com.mhsenpc.v2raybot.bot.enums.PaymentMethod;

public class BuyAccountRequest {
    private int planId;
    private PaymentMethod paymentMethod;

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
                ", planId=" + planId +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
