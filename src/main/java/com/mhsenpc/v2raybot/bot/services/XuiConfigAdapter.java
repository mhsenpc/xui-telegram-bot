package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;

public class XuiConfigAdapter extends XuiConfig {

    private Config config;

    public XuiConfigAdapter(Config config) {
        this.config = config;
    }

    @Override
    public String getBaseUrl() {
        return config.getBaseUrl();
    }

    @Override
    public String getUsername() {
        return config.getUsername();
    }

    @Override
    public String getPassword() {
        return config.getPassword();
    }
}
