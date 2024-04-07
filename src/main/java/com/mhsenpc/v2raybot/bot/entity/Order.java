package com.mhsenpc.v2raybot.bot.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int status;
    private Date createdAt;

    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "order"
    )
    private Transaction transaction;

    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "order"
    )
    private VlessAccount vlessAccount;

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public VlessAccount getVlessAccount() {
        return vlessAccount;
    }

    public void setVlessAccount(VlessAccount vlessAccount) {
        this.vlessAccount = vlessAccount;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
