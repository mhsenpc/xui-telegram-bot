package com.mhsenpc.v2raybot.bot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mhsenpc.v2raybot.xui.XUIManager;
import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.enums.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @Autowired
    private XUIManager xui;

    @GetMapping("/")
    public String home()  {

        return "Hi";
    }
}
