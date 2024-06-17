package com.mhsenpc.v2raybot.bot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String showWelcome(){


        return "Welcome to XUI bot";
    }
}
