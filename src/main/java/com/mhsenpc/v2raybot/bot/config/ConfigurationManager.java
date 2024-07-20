package com.mhsenpc.v2raybot.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Component
@Slf4j
public class ConfigurationManager {

    private Map<String, String> configMap;

    @Value("${config.file.path}")
    private String configFilePath;

    @PostConstruct
    public void init() {

        File configFile = new File(configFilePath);

        if (!configFile.exists()) {
            CreateExampleConfig();
            String message = "Configuration file (config.json) not found. Please create one and restart the program.";
            System.out.println(message);
            log.error(message);
            System.exit(1);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            configFile = new File(configFilePath);
            if (configFile.exists()) {
                configMap = objectMapper.readValue(configFile, Map.class);
            } else {
                throw new RuntimeException("Configuration file not found. Please run the application with the appropriate setup.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public String getConfig(ConfigName configName) {
        return configMap.get(configName.name());
    }

    public void CreateExampleConfig() {
        try (InputStream exampleConfigStream = getClass().getClassLoader().getResourceAsStream("config.json.example")) {
            if (exampleConfigStream == null) {
                throw new RuntimeException("Example config file not found");
            }
            File configFile = new File(configFilePath + ".example" );
            Files.copy(exampleConfigStream, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize example configuration file", e);
        }
    }
}