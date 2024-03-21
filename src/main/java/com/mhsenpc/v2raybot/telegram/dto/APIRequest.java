package com.mhsenpc.v2raybot.telegram.dto;

import com.mhsenpc.v2raybot.telegram.Requestable;

abstract class APIRequest implements Requestable {
    private String baseUrl = "https://api.telegram.org/bot";

    private String methodName;

    protected String token;

    public APIRequest() {
    }

    public APIRequest(String methodName, String token) {
        this.methodName = methodName;
        this.token = token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestUrl(){
        return baseUrl + token + "/" + methodName;
    }
}
