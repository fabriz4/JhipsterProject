package org.tvbookmarks.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
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

    public SerieTVservice(ApplicationProperties properties){
    this.properties=properties;
    ApiKey=properties.getDbmovie().getApiKey();
    BaseURL=properties.getDbmovie().getBaseURL();
    }

    public String findSerieTV(String name){
        String result= null;

        try {
            result=callDBMovieAPI(name);

        }catch (Exception e){
            log.info("servizio esterno exception");
            return null;
        }

        return result;
    }
    private String callDBMovieAPI(String name){
        String path="search/tv?api_key="+ ApiKey +"&query=";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template=new RestTemplate();
        ResponseEntity<String> responseEntity = template.exchange(BaseURL + path + name, HttpMethod.GET,
            new HttpEntity<>(headers), String.class);
        String Json = callJsonObject(responseEntity.getBody()).toString();
        return Json;
    }

    private JSONObject callJsonObject(String response) {
        log.info("errore "+ response);

        try {
            JSONObject resultJSON = new JSONObject(response);
            JSONObject results = new JSONObject(resultJSON.getString("results"));
            return results;
        } catch (JSONException e) {
            log.debug("Json exception!");
            return null;
        }
    }
}
