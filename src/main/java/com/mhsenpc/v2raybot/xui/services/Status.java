package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.SystemInfoResponse;
import com.mhsenpc.v2raybot.xui.exceptions.InvalidXUIBaseUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class Status {
    private String baseUrl;

    public String getBaseUrl() {

        if(baseUrl.isEmpty()){
            throw new InvalidXUIBaseUrl();
        }

        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    private CookieManager cookieManager;

    public SystemInfoResponse getStatus(){

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("cookie", cookieManager.getCookie());

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
            ResponseEntity<SystemInfoResponse> responseEntity = restTemplate.exchange(this.getBaseUrl() + "/server/status", HttpMethod.POST, requestEntity, SystemInfoResponse.class);
            if(responseEntity.hasBody()){
                SystemInfoResponse systemInfoResponse = responseEntity.getBody();
                return systemInfoResponse;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public boolean check(){

        SystemInfoResponse systemInfoResponse = getStatus();
        return systemInfoResponse != null && systemInfoResponse.isSuccess();
    }
}
