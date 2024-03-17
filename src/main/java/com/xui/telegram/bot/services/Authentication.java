package com.xui.telegram.bot.services;

import com.xui.telegram.bot.config.PanelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class Authentication {

    @Autowired
    PanelConfig panelConfig;

    private String cookieFileName = "storage/panel.cookie";

    public boolean Auth(){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", panelConfig.getUserName());
        formData.add("password", panelConfig.getPassword());

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create HTTP entity with headers and form data
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        // Send login request
        ResponseEntity<String> responseEntity = restTemplate.exchange(panelConfig.getBaseUrl() + "/login", HttpMethod.POST, requestEntity, String.class);

        // Get cookies from response headers
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        List<String> cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);

        // Store cookies to your directory or wherever you need
        if (cookies != null) {
            for (String cookie : cookies) {
                // Store cookies to your directory or wherever you need
                System.out.println("Cookie: " + cookie);

                if(cookie.startsWith("session=")) {
                    try (FileWriter writer = new FileWriter(cookieFileName)) {
                        writer.write(cookie);
                        System.out.println("Successfully wrote to the file: " + cookieFileName);
                        return true;
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + cookieFileName);
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No cookies found in the response.");
        }
        return false;
    }
}
