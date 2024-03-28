package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected Config config;

    public BaseController() {

        ConfigurationManager configurationManager = new ConfigurationManager();
        this.config = configurationManager.getConfig();
    }
}
