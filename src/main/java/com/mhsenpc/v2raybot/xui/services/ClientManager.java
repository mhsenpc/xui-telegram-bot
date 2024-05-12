package com.mhsenpc.v2raybot.xui.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.dto.ClientSettings;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.v2raybot.xui.exceptions.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ClientManager {

    @Autowired
    private Status status;

    @Autowired Authentication authentication;

    @Autowired
    private InboundService inboundService;

    private XuiConfig xuiConfig;

    public void setXuiConfig(XuiConfig xuiConfig) {
        this.xuiConfig = xuiConfig;
    }

    public int getInboundId() throws InboundNotRetrievedException {

        inboundService.setXuiConfig(this.xuiConfig);
        return this.inboundService.getActiveInbound().getId();
    }

    @Autowired
    private CookieManager cookieManager;

    public CreateUserResponse save(XUIClient XUIClient) throws UnauthenticatedException, IOException, InboundNotRetrievedException {

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // save client to client settings
        ClientSettings clientSettings = new ClientSettings();
        clientSettings.setClients(List.of(XUIClient));

        // Convert Payload to JSON
        ObjectMapper mapper = new ObjectMapper();

        String clientSettingsJson = mapper.writeValueAsString(clientSettings);

        // create form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", String.valueOf(getInboundId()));
        formData.add("settings", clientSettingsJson);

        this.validateCookie();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("cookie", cookieManager.getCookie());

        // Set the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        // Send the POST request
        ResponseEntity<CreateUserResponse> response = restTemplate.exchange(this.xuiConfig.getBaseUrl() + "/panel/inbound/addClient", HttpMethod.POST, requestEntity, CreateUserResponse.class);
        return response.getBody();
    }

    private void validateCookie() throws UnauthenticatedException, IOException {

        status.setXuiConfig(xuiConfig);
        if(!status.check()){
            authentication.login(xuiConfig.getBaseUrl(), xuiConfig.getUsername(), xuiConfig.getPassword());

            if(!status.check()){
                throw new UnauthenticatedException();
            }
        }
    }
}
