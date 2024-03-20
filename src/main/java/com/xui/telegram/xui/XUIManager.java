package com.xui.telegram.xui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xui.telegram.xui.dto.Client;
import com.xui.telegram.xui.dto.CreateUserResponse;
import com.xui.telegram.xui.dto.SystemInfoResponse;
import com.xui.telegram.xui.services.Authentication;
import com.xui.telegram.xui.services.ClientManager;
import com.xui.telegram.xui.services.Status;
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
