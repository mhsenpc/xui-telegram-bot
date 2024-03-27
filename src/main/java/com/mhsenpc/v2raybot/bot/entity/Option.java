package com.mhsenpc.v2raybot.bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Option {

    @Id
    private String optionKey;
    private String optionValue;

    public Option() {
    }

    public Option(String optionKey, String optionValue) {
        this.optionKey = optionKey;
        this.optionValue = optionValue;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
