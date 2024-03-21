package com.mhsenpc.v2raybot.bot.controllers;

import com.mhsenpc.v2raybot.xui.XUIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private XUIManager xui;

    @GetMapping("/")
    public String home()  {

        return "Hi";
    }
}
