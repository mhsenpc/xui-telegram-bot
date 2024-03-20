package com.xui.telegram.xui.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xui.telegram.bot.config.PanelConfig;
import com.xui.telegram.xui.dto.Client;
import com.xui.telegram.xui.dto.ClientSettings;
import com.xui.telegram.xui.dto.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientManager {

    @Autowired
    PanelConfig panelConfig;

    @Autowired
    private CookieManager cookieManager;

    public CreateUserResponse save(Client client) throws JsonProcessingException {

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // save client to client settings
        ClientSettings clientSettings = new ClientSettings();
        clientSettings.setClients(List.of(client));

        // Convert Payload to JSON
        ObjectMapper mapper = new ObjectMapper();
        String clientSettingsJson;

        clientSettingsJson = mapper.writeValueAsString(clientSettings);

        // create form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", "3"); // todo: this should not be hard-coded
        formData.add("settings", clientSettingsJson);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("cookie", cookieManager.getCookie());

        // Set the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        // Send the POST request
        ResponseEntity<CreateUserResponse> response = restTemplate.exchange(panelConfig.getBaseUrl() + "/panel/inbound/addClient", HttpMethod.POST, requestEntity, CreateUserResponse.class);
        return response.getBody();
    }
}
