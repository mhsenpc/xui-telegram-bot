package com.mhsenpc.v2raybot.telegram.services;

import com.mhsenpc.v2raybot.telegram.methods.BaseTelegramMethod;
import com.mhsenpc.v2raybot.telegram.types.IResponse;
import com.mhsenpc.v2raybot.telegram.methods.Executable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestHandler {

    public <T extends IResponse> T send(BaseTelegramMethod request, Class<T> responseType){

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set the request entity
        HttpEntity<Executable> requestEntity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<T> response = restTemplate.exchange(request.getRequestUrl(), HttpMethod.POST, requestEntity, responseType );
        return response.getBody();
    }
}
