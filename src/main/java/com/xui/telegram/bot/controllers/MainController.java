package com.xui.telegram.bot.controllers;

import com.xui.telegram.bot.dto.SystemInfoResponse;
import com.xui.telegram.bot.services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private Authentication authentication;

    @GetMapping("/")
    public SystemInfoResponse authenticate(){
        SystemInfoResponse systemInfoResponse = authentication.status();
        return  systemInfoResponse;
    }
}
