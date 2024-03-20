package com.xui.telegram.bot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xui.telegram.xui.dto.Client;
import com.xui.telegram.xui.dto.CreateUserResponse;
import com.xui.telegram.xui.XUIManager;
import com.xui.telegram.xui.enums.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MainController {

    @Autowired
    private XUIManager xui;

    @GetMapping("/")
    public CreateUserResponse authenticate() throws JsonProcessingException {
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setEmail("tetssda");
        client.setFlow(Flow.XTLS_RPRX_VISION);
        client.setLimitIp(3);
        client.setTrafficInGB(100);
        client.setEnable(true);
        client.setSubId("t8uhn7khsg9srdsg");
        client.setExpiryTime(0);


        return xui.createUser(client);


    }
}
