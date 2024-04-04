package com.mhsenpc.v2raybot.bot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;
    private String title;
    private float price;
    private int months;
    private int trafficLimit;
    private int connectionLimit;

    public Plan() {
    }

    public Plan(String title, float price, int months, int trafficLimit, int connectionLimit) {
        this.title = title;
        this.price = price;
        this.months = months;
        this.trafficLimit = trafficLimit;
        this.connectionLimit = connectionLimit;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
        return "Plan{" +
                "planId=" + planId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", months=" + months +
                ", trafficLimit=" + trafficLimit +
                ", connectionLimit=" + connectionLimit +
                '}';
    }
}
