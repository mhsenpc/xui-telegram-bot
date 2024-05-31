package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConfigController extends BaseController {

    @Autowired
    private ConfigurationManager configurationManager;

    @GetMapping("/config")
    public String showConfig(Model model){

        model.addAttribute("config", config);
        return "config";
    }

    @PostMapping("config")
    public String storeConfig(@ModelAttribute("config") Config newConfig){
        this.configurationManager.setConfig(newConfig);

        this.config = newConfig;

        return "redirect:/";
    }
}
