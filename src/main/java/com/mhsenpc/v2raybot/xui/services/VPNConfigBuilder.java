package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.Inbound;
import com.mhsenpc.v2raybot.xui.dto.StreamSettings;
import com.mhsenpc.v2raybot.xui.dto.XUIClient;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import com.mhsenpc.v2raybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VPNConfigBuilder {

    private final String UNSECURE_PATTERN = "%s://%s@%s:%s?type=tcp&security=%s#%s";
    private final String REALITY_PATTERN = "%s://%s@%s:%s?type=tcp&security=%s&pbk=%s&fp=%s&sni=%s&sid=%s&spx=%%2F&flow=%s#%s";

    @Autowired
    private InboundService inboundService;

    @Value("${xui.host}")
    private String HOST;

    @Value("${xui.port}")
    private String PORT;

    private XUIClient xuiClient;

    public VPNConfigBuilder setClient(XUIClient xuiClient){
        this.xuiClient = xuiClient;
        return this;
    }

    public VPNConfigBuilder setXUIConfig(XuiConfig xuiConfig){
        inboundService.setXuiConfig(xuiConfig);
        return this;
    }

    public String build() throws InboundNotRetrievedException {

        Inbound inbound = inboundService.getDefaultInbound();
        StreamSettings streamSettings = inbound.getStreamSettings();
        switch (streamSettings.getSecurity()){
            case "none" -> {
                return String.format(UNSECURE_PATTERN,
                        inbound.getProtocol(),
                        xuiClient.getId(),
                        HOST,
                        PORT,
                        streamSettings.getSecurity(),
                        xuiClient.getEmail()
                );
            }
            case "reality" -> {
                return String.format(REALITY_PATTERN,
                        inbound.getProtocol(),
                        xuiClient.getId(),
                        HOST,
                        PORT,
                        streamSettings.getSecurity(),
                        streamSettings.getRealitySettings().getSettings().getPublicKey(),
                        streamSettings.getRealitySettings().getSettings().getFingerprint(),
                        streamSettings.getRealitySettings().getServerNames().get(0),
                        streamSettings.getRealitySettings().getShortIds().get(0),
                        xuiClient.getFlow(),
                        xuiClient.getEmail()
                 );
            }
        }
        return "Technical error: cannot generate vpn config url";
    }
}
