package com.mhsenpc.v2raybot.telegram.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mhsenpc.v2raybot.telegram.interfaces.Requestable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public abstract class APIRequest implements Requestable {
    @JsonIgnore
    private String baseUrl = "https://api.telegram.org/bot";

    @JsonIgnore
    private String methodName;

    @JsonIgnore
    private String token;

    @JsonIgnore
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

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
        return baseUrl + token + "/" + methodName +  "?" + queryParamsToQueryString(queryParams);
    }

    public MultiValueMap<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(MultiValueMap<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void addQueryParam(String key, String value){
        this.queryParams.add(key, value);
    }

    public void clearQueryParams(){
        this.queryParams.clear();
    }

    private String queryParamsToQueryString(MultiValueMap<String, String> queryParams) {
        StringBuilder queryString = new StringBuilder();
        for (String key : queryParams.keySet()) {
            for (String value : queryParams.get(key)) {
                if (queryString.length() > 0) {
                    queryString.append("&");
                }
                queryString.append(key).append("=").append(value);
            }
        }
        return queryString.toString();
    }

}
