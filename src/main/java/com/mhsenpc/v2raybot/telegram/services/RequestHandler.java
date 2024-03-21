package com.mhsenpc.v2raybot.telegram.services;

import com.mhsenpc.v2raybot.telegram.Requestable;
import com.mhsenpc.v2raybot.telegram.dto.MessageResponse;
import com.mhsenpc.v2raybot.xui.dto.CreateUserResponse;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RequestHandler {

    public MessageResponse send(Requestable request){

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the request entity
        HttpEntity<Requestable> requestEntity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<MessageResponse> response = restTemplate.exchange(request.getBaseUrl(), HttpMethod.POST, requestEntity, MessageResponse.class);
        return response.getBody();
    }
}
