package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private int role;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, and setters
    public Role() {}

    public Role(int role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}