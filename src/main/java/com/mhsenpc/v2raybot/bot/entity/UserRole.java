package com.mhsenpc.v2raybot.bot.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;
    private int role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, and setters
    public UserRole() {}

    public UserRole(com.mhsenpc.v2raybot.bot.enums.UserRole role) {
        this.role = role.getValue();
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}