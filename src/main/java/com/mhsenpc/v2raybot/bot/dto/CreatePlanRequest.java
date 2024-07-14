package com.mhsenpc.v2raybot.bot.dto;

public class CreatePlanRequest {

    private float price;
    private int months;
    private int trafficLimit;
    private int connectionLimit;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(int trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    public int getConnectionLimit() {
        return connectionLimit;
    }

    public void setConnectionLimit(int connectionLimit) {
        this.connectionLimit = connectionLimit;
    }

    @Override
    public String toString() {
        return "CreatePlanRequest{" +
                "price=" + price +
                ", months=" + months +
                ", trafficLimit=" + trafficLimit +
                ", connectionLimit=" + connectionLimit +
                '}';
    }
}
