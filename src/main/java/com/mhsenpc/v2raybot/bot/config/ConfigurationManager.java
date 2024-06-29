package com.mhsenpc.v2raybot.bot.config;

import com.mhsenpc.v2raybot.bot.entity.Option;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import com.mhsenpc.v2raybot.bot.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {

    @Autowired
    private OptionRepository optionRepository;

    public String getConfig(ConfigName configName){

        Option configRecord = optionRepository.findFirstByKey(configName.name());
        if(configRecord == null) {
            return "";
        }
        return configRecord.getValue();
    }

    public void setConfig(ConfigName configName, String value){

        Option configRecord = optionRepository.findFirstByKey(configName.name());
        if (configRecord != null) {
            configRecord.setValue(value);
        } else {
            configRecord = new Option(configName.name(), value);
        }
        optionRepository.save(configRecord);
    }
}
