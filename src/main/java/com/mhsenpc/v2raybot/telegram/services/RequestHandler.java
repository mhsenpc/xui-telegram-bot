package com.mhsenpc.v2raybot.telegram.services;

import com.mhsenpc.v2raybot.telegram.methods.BaseTelegramMethod;
import com.mhsenpc.v2raybot.telegram.methods.Executable;
import com.mhsenpc.v2raybot.telegram.methods.SetWebhookMethod;
import com.mhsenpc.v2raybot.telegram.types.IResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
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

    public <T extends IResponse> T sendWebhookRequest(SetWebhookMethod request, Class<T> responseType) {

        RestTemplate restTemplate = new RestTemplate();

        // Build multipart form data
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("url", request.getUrl());
        builder.part("certificate", new InputStreamResource(request.getCertificate()))
                .header("Content-Disposition", "form-data; name=certificate; filename=certificate.pem");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> requestEntity = new HttpEntity<>(builder.build(), headers);

        // Send the POST request
        ResponseEntity<T> response = restTemplate.exchange(request.getRequestUrl(), HttpMethod.POST, requestEntity, responseType );
        return response.getBody();
    }


}
