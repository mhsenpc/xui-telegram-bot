package com.xui.telegram.bot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Authentication {

    @Value("${xui.base.url}")
    private String baseUrl;

    @Value("${xui.username}")
    private String userName;

    @Value("${xui.password}")
    private String password;


    public String Auth(){
        return baseUrl + userName + password;
    }
}
