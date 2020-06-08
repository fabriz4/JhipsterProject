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
import org.tvbookmarks.app.domain.DatiSerieTV;

import java.util.ArrayList;
import java.util.List;


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

    public List<DatiSerieTV> findSerieTV(String name){
        List<DatiSerieTV> result= null;

        try {
            result=callDBMovieAPI(name);

        }catch (Exception e){
            log.info("servizio esterno exception");
            return null;
        }

        return result;
    }
    private List<DatiSerieTV> callDBMovieAPI(String name){
        String path="search/tv?api_key="+ ApiKey +"&query=";
        HttpHeaders headers = new HttpHeaders();
        RestTemplate template=new RestTemplate();
        ResponseEntity<String> responseEntity = template.exchange(BaseURL + path + name, HttpMethod.GET,
            new HttpEntity<>(headers), String.class);
        List<DatiSerieTV> Json = responseConverter(responseEntity.getBody());
        return Json;
    }

    private List<DatiSerieTV> responseConverter(String response) {
        log.info("errore "+ response);

        try {
            JSONObject resultJSON = new JSONObject(response);
            JSONArray resultsArray = new JSONArray(resultJSON.getString("results"));
            List<DatiSerieTV> result=new ArrayList<>();

            for(int i=0;i<resultsArray.length();i++){

                resultJSON = resultsArray.getJSONObject(i);
                DatiSerieTV datiSerieTV=new DatiSerieTV();
                datiSerieTV.setName(resultJSON.getString("name"));
                datiSerieTV.setOverview(resultJSON.getString("overview"));
                datiSerieTV.setAvg_vote(resultJSON.getDouble("vote_average"));
                datiSerieTV.setPopularity(resultJSON.getDouble("popularity"));

                result.add(datiSerieTV);
            }

            return  result;
        } catch (JSONException e) {
            log.debug("Json exception!:" + e);
            return null;
        }
    }
}
