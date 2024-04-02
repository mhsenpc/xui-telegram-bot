package com.mhsenpc.v2raybot.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private float amount;

    private String description;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    // Constructors, Getters, and Setters
    // Constructor

    public Transaction() {
    }

    public Transaction(float amount, String description, Date createdAt) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> order) {
        this.orders = order;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}