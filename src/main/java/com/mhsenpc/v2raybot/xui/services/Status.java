package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.SystemInfoResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Status {

    private XuiConfig xuiConfig;

    public void setXuiConfig(XuiConfig xuiConfig) {
        this.xuiConfig = xuiConfig;
    }

    @Autowired
    private CookieManager cookieManager;

    protected SystemInfoResponse getStatus(){

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("cookie", cookieManager.getCookie());

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
            ResponseEntity<SystemInfoResponse> responseEntity = restTemplate.exchange(this.xuiConfig.getBaseUrl() + "/server/status", HttpMethod.POST, requestEntity, SystemInfoResponse.class);
            if(responseEntity.hasBody()){
                SystemInfoResponse systemInfoResponse = responseEntity.getBody();
                return systemInfoResponse;
            }
        }
        catch (Exception exception){
            log.error(exception.getMessage());
        }

        return null;
    }

    public boolean check(){

        SystemInfoResponse systemInfoResponse = getStatus();
        return systemInfoResponse != null && systemInfoResponse.isSuccess();
    }
}
