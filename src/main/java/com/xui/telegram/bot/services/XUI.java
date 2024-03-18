package com.xui.telegram.bot.services;

import com.xui.telegram.bot.dto.SystemInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XUI {

    @Autowired
    Authentication authentication;

    @Autowired
    Status status;

    public boolean login(){
        return authentication.login();
    }

    public SystemInfoResponse status(){
        return status.getStatus();
    }

    public boolean check(){
        return status.check();
    }
}
