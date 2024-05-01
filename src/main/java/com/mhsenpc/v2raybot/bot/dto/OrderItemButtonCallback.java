package com.mhsenpc.v2raybot.bot.dto;

import com.mhsenpc.v2raybot.bot.enums.OrderCommandType;

public class OrderItemButtonCallback {

    private int orderId;
    private OrderCommandType commandType;

    public OrderItemButtonCallback() {
    }

    public OrderItemButtonCallback(int orderId, OrderCommandType commandType) {
        this.orderId = orderId;
        this.commandType = commandType;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderCommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(OrderCommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return "OrderItemButtonCallback{" +
                "orderId=" + orderId +
                ", commandType=" + commandType +
                '}';
    }
}
