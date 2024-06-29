package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Inbound;
import com.mhsenpc.v2raybot.xui.dto.InboundsListResponse;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class InboundService {

    private XuiConfig xuiConfig;

    public void setXuiConfig(XuiConfig xuiConfig) {
        this.xuiConfig = xuiConfig;
    }

    @Autowired
    private CookieManager cookieManager;


    public Inbound getDefaultInbound() throws InboundNotRetrievedException {

        InboundsListResponse inboundsListResponse = getAllInbounds();
        if(inboundsListResponse == null || !inboundsListResponse.isSuccess() || inboundsListResponse.getInbounds().isEmpty()){
            throw new InboundNotRetrievedException();
        }

        Inbound inbound;
        try{
            inbound = getOverrideInbound(inboundsListResponse);
        }
        catch (InboundNotRetrievedException exception){
            inbound = getActiveInbound(inboundsListResponse);
        }

        return inbound;
    }

    public Inbound getOverrideInbound(InboundsListResponse inboundsListResponse) throws InboundNotRetrievedException {

        int overrideInboundId = Integer.parseInt(this.xuiConfig.getOverrideInboundId());

        for(Inbound inbound: inboundsListResponse.getInbounds()){
            if(inbound.getId() == overrideInboundId ){
                return inbound;
            }
        }
        throw new InboundNotRetrievedException();
    }

    public Inbound getActiveInbound(InboundsListResponse inboundsListResponse) throws InboundNotRetrievedException {

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
                return responseEntity.getBody();
            }
        }
        catch (Exception exception){
            log.error(exception.getMessage());

        }
        return null;
    }
}
