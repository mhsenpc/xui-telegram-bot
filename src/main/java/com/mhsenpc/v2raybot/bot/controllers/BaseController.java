package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    private ConfigurationManager configurationManager;

    protected Config config;

    @PostConstruct
    public void initializeConfig(){

        this.config = this.configurationManager.getConfig();
    }
}
