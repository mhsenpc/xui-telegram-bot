package com.mhsenpc.v2raybot.bot.controllers;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CommonsLog
public class HomeController extends BaseController {

    @GetMapping("/")
    public String home() {

        return "home";
    }
}
