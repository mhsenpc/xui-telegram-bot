package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Inbound;
import com.mhsenpc.v2raybot.xui.dto.InboundsListResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
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
public class InboundService {

    private XuiConfig xuiConfig;

    public void setXuiConfig(XuiConfig xuiConfig) {
        this.xuiConfig = xuiConfig;
    }

    @Autowired
    private CookieManager cookieManager;

    public Inbound getActiveInbound() throws InboundNotRetrievedException {

        InboundsListResponse inboundsListResponse = getAllInbounds();
        if(inboundsListResponse == null || !inboundsListResponse.isSuccess() || inboundsListResponse.getInbounds().isEmpty()){
            throw new InboundNotRetrievedException();
        }

        for(Inbound inbound: inboundsListResponse.getInbounds()){
            if(inbound.isEnable()){
                return inbound;
            }
        }
        throw new InboundNotRetrievedException();
    }

    protected InboundsListResponse getAllInbounds(){

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("cookie", cookieManager.getCookie());

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
            ResponseEntity<InboundsListResponse> responseEntity = restTemplate.exchange(this.xuiConfig.getBaseUrl() + "/panel/inbound/list", HttpMethod.POST, requestEntity, InboundsListResponse.class);
            if(responseEntity.hasBody()){
                InboundsListResponse inboundsListResponse = responseEntity.getBody();
                return inboundsListResponse;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());

        }
        return null;
    }
}
