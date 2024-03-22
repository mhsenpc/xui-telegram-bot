package com.mhsenpc.v2raybot.telegram.services;

import com.mhsenpc.v2raybot.telegram.dto.SetWebhookResponse;
import com.mhsenpc.v2raybot.telegram.interfaces.Requestable;
import com.mhsenpc.v2raybot.telegram.dto.APIRequest;
import com.mhsenpc.v2raybot.telegram.dto.MessageResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandler {

    public SetWebhookResponse send(APIRequest request){

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the request entity
        HttpEntity<Requestable> requestEntity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<SetWebhookResponse> response = restTemplate.exchange(request.getRequestUrl(), HttpMethod.POST, requestEntity, SetWebhookResponse.class);
        return response.getBody();

    }
}
