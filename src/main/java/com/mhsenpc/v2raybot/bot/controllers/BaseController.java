package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    protected Config config;

    public BaseController() {

        this.config = Config.getInstance();
    }
}
