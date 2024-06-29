package com.mhsenpc.v2raybot.bot.config;

import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import com.mhsenpc.v2raybot.xui.dto.XuiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XUIConfigBuilder {

    @Autowired
    private static ConfigurationManager configurationManager;

    public static XuiConfig build(){

        return new XuiConfig(
                configurationManager.getConfig(ConfigName.PANEL_BASE_URL),
                configurationManager.getConfig(ConfigName.PANEL_USERNAME),
                configurationManager.getConfig(ConfigName.PANEL_PASSWORD)
        );
    }
}
