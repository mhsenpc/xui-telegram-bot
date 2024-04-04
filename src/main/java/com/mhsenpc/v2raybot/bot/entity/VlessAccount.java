package com.mhsenpc.v2raybot.bot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vless_accounts")
public class VlessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vlessAccountId;

    private String name;

    private String url;

    private String uuid;

    private Date createdAt;

    private Date validUntil;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    public int getVlessAccountId() {
        return vlessAccountId;
    }

    public void setVlessAccountId(int vlessAccountId) {
        this.vlessAccountId = vlessAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "VlessAccount{" +
                "vlessAccountId=" + vlessAccountId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", uuid='" + uuid + '\'' +
                ", createdAt=" + createdAt +
                ", validUntil=" + validUntil +
                ", user=" + user +
                '}';
    }
}