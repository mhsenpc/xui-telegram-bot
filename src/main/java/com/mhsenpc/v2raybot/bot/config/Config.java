package com.mhsenpc.v2raybot.bot.config;

public class Config {

    private static Config instance;

    private String token;

    private String baseUrl;

    private String username;

    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Config getInstance(){

        if(instance == null){
            ConfigurationManager configurationManager = new ConfigurationManager();
            instance = configurationManager.getConfig();
        }

        return instance;
    }

    @Override
    public String toString() {
        return "Config{" +
                "token='" + token + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
