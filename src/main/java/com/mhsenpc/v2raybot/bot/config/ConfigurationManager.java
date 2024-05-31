package com.mhsenpc.v2raybot.bot.config;

import com.mhsenpc.v2raybot.bot.entity.Option;
import com.mhsenpc.v2raybot.bot.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {
    private final String CONFIG_KEY_PANEL_BASE_URL = "PANEL_BASE_URL";
    private final String CONFIG_KEY_PANEL_USERNAME = "PANEL_USERNAME";
    private final String CONFIG_KEY_PANEL_PASSWORD = "PANEL_PASSWORD";
    private final String CONFIG_KEY_BOT_TOKEN = "BOT_TOKEN";

    @Autowired
    private OptionRepository optionRepository;

    private Config config = new Config();

    public void setConfig(Config newConfig) {
        this.config = newConfig;
        updateConfig();
    }

    public Config getConfig() {
        loadConfig();
        return this.config;
    }

    private void loadConfig() {

        Option panelBaseUrlOption = optionRepository.findFirstByKey(CONFIG_KEY_PANEL_BASE_URL);
        Option panelUserNameOption = optionRepository.findFirstByKey(CONFIG_KEY_PANEL_USERNAME);
        Option panelPasswordOption = optionRepository.findFirstByKey(CONFIG_KEY_PANEL_PASSWORD);
        Option botTokenOption = optionRepository.findFirstByKey(CONFIG_KEY_BOT_TOKEN);

        if(panelBaseUrlOption != null) {
            config.setBaseUrl(panelBaseUrlOption.getValue());
        }

        if(panelUserNameOption != null) {
            config.setUsername(panelUserNameOption.getValue());
        }

        if(panelPasswordOption != null) {
            config.setPassword(panelPasswordOption.getValue());
        }

        if(botTokenOption != null) {
            config.setToken(botTokenOption.getValue());
        }
    }

    private void updateConfig() {
        Option panelBaseUrlOption = new Option(CONFIG_KEY_PANEL_BASE_URL, config.getBaseUrl());
        Option panelUserNameOption = new Option(CONFIG_KEY_PANEL_USERNAME, config.getUsername());
        Option panelPasswordOption = new Option(CONFIG_KEY_PANEL_PASSWORD, config.getPassword());
        Option botTokenOption = new Option(CONFIG_KEY_BOT_TOKEN, config.getToken());

        this.optionRepository.save(panelBaseUrlOption);
        this.optionRepository.save(panelUserNameOption);
        this.optionRepository.save(panelPasswordOption);
        this.optionRepository.save(botTokenOption);
    }
}
