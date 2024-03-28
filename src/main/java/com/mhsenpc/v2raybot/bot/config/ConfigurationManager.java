package com.mhsenpc.v2raybot.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class ConfigurationManager {
    private Config config;
    private final String filePath = "storage/config.json";

    public ConfigurationManager() {
        this.config = readConfigFromFile();
    }

    public void updateConfig() {
        writeConfigToFile();
    }

    public void setConfig(Config config) {
        this.config = config;
        updateConfig();
    }

    public Config getConfig() {
        return this.config;
    }

    private Config readConfigFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            if (file.exists()) {
                return mapper.readValue(file, Config.class);
            } else {
                return new Config(); // Create a new Config object if JSON file doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeConfigToFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
