package com.mhsenpc.v2raybot.xui.services;

import com.mhsenpc.v2raybot.xui.dto.XUIClient;

public class VPNConfig {
    private XUIClient xuiClient;

    public VPNConfig(XUIClient xuiClient) {
        this.xuiClient = xuiClient;
    }

    public String getConfig(){

        // vless://663b476b-3bc6-4c61-bef9-8f4df9b6fc0b@5.75.199.188:46478?type=tcp&security=reality&pbk=_jBL_tPfj6g03tsGXVunZFFDx_HrxBuvciXD7i80Jws&fp=firefox&sni=yahoo.com&sid=05a7f368&spx=%2F&flow=xtls-rprx-vision#user_BzNFB
        return "todo:// implement this method";
    }
}
