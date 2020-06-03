package org.tvbookmarks.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.tvbookmarks.app.config.ApplicationProperties;



@Service

public class SerieTVservice {

    private final Logger log = LoggerFactory.getLogger(SerieTVservice.class);
    private ApplicationProperties properties;
    private String BaseURL;
    private String ApiKey;

    public SerieTVservice(ApplicationProperties properties) {
    this.properties=properties;
    ApiKey=properties.getDbmovie().getApiKey();
    BaseURL=properties.getDbmovie().getBaseURL();
    }

    public JSONObject findSerieTV(String name){
        String path="search/tv?query=";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template=new RestTemplate();
        ResponseEntity<JSONObject> responseEntity = template.exchange(BaseURL + path + name, HttpMethod.GET,
            new HttpEntity<>(headers), JSONObject.class);
        return responseEntity.getBody();
    }
}
