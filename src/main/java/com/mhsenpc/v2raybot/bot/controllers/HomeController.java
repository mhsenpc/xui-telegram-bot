package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.BotConfig;
import com.mhsenpc.v2raybot.xui.XUIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BotConfig botConfig;

    @Autowired
    private XUIManager xui;

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("token", botConfig.getToken());
        return "home";
    }
}
