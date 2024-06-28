package com.mhsenpc.v2raybot.bot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {

    public Option() {
    }

    public Option(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Id
    @Column(unique=true, name = "option_key")
    private String key;

    @Column(name = "option_value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
