package com.mhsenpc.v2raybot.xui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mhsenpc.v2raybot.xui.dto.Client;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.dto.SystemInfoResponse;
import com.mhsenpc.v2raybot.xui.services.ClientManager;
import com.mhsenpc.v2raybot.xui.services.Status;
import com.mhsenpc.v2raybot.xui.services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XUIManager {

    @Autowired
    Authentication authentication;

    @Autowired
    Status status;

    @Autowired
    ClientManager clientManager;

    public boolean login(){
        return authentication.login();
    }

    public SystemInfoResponse status(){
        return status.getStatus();
    }

    public boolean check(){
        return status.check();
    }

    public CreateUserResponse createUser(Client client) throws JsonProcessingException {
        return clientManager.save(client);
    }
}
