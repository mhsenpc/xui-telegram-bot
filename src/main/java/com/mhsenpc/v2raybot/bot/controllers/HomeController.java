package com.mhsenpc.v2raybot.bot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public String home() {

        return "home";
    }
}
