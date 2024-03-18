package com.xui.telegram.xui.services;

import com.xui.telegram.bot.config.PanelConfig;
import com.xui.telegram.xui.dto.Client;
import com.xui.telegram.xui.dto.CreateUserResponse;
import com.xui.telegram.xui.dto.SystemInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ClientManager {

    @Autowired
    PanelConfig panelConfig;

    @Autowired
    private CookieManager cookieManager;

    public CreateUserResponse save(Client client){

        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("cookie", cookieManager.getCookie());

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        // Create HTTP entity with headers and form data
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        ResponseEntity<CreateUserResponse> responseEntity = restTemplate.exchange(panelConfig.getBaseUrl() + "/server/status", HttpMethod.POST, requestEntity, CreateUserResponse.class);

        CreateUserResponse createUserResponse = responseEntity.getBody();
        return createUserResponse;
    }
}
