package org.tvbookmarks.app.web.rest;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tvbookmarks.app.service.SerieTVservice;


import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller to call External API.
 */
@RestController
@RequestMapping("/api")
public class ExternalAPIController {


    @Autowired
    private SerieTVservice service;

@GetMapping("/search")
public ResponseEntity<String> findTV(@RequestParam String name){

    String response=service.findSerieTV(name);
    return ok(response);

}


}

