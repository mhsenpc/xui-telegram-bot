package com.mhsenpc.v2raybot.xui.services;

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
public class Authentication {

    @Autowired
    private CookieManager cookieManager;

    public boolean login(String baseUrl, String username, String password) throws UnauthenticatedException, IOException {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create HTTP entity with headers and form data
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        // Send login request
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, requestEntity, String.class);

        // Get cookies from response headers
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        List<String> cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);

        // Store cookies to your directory or wherever you need
        if(cookies == null){
            throw new UnauthenticatedException();
        }

        for (String cookie : cookies) {
            // Store cookies to your directory or wherever you need
            System.out.println("Cookie: " + cookie);

            if (cookie.startsWith("session=")) {
                cookieManager.setCookie(cookie);
            }
        }
        return true;
    }
}
