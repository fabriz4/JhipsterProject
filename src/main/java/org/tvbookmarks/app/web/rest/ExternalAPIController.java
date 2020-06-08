package org.tvbookmarks.app.web.rest;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tvbookmarks.app.config.DatabaseConfiguration;
import org.tvbookmarks.app.domain.DatiSerieTV;
import org.tvbookmarks.app.service.SerieTVservice;


import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller to call External API.
 */
@RestController
@RequestMapping(value = "/api", produces ={ MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
public class ExternalAPIController {


    @Autowired
    private SerieTVservice service;

@GetMapping("/search")
public ResponseEntity<List<DatiSerieTV>> findTV(@RequestParam String name){

    List<DatiSerieTV> response=service.findSerieTV(name);
    return ok(response);

}


}

