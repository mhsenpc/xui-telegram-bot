package com.xui.telegram.bot.services;

import com.xui.telegram.bot.config.PanelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@Service
public class Authentication {

    @Autowired
    private PanelConfig panelConfig;

    @Autowired
    private CookieManager cookieManager;

    public boolean login(){

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
                    try{
                        cookieManager.setCookie(cookie);
                        return true;
                    } catch (IOException e) {
                        System.out.println("An error occurred while storing the cookie ");
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No cookies found in the response.");
        }
        return false;
    }

    public boolean check(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie", cookieManager.getCookie() );
        // /server/status
        return true;
    }
}
