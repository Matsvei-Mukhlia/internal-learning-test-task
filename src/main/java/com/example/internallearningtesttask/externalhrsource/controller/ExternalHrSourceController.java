package com.example.internallearningtesttask.externalhrsource.controller;

import com.example.internallearningtesttask.externalhrsource.ExternalHrSource;
import com.example.internallearningtesttask.externalhrsource.service.ExternalHrSourceService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static com.example.internallearningtesttask.Constant.REQRES;
import static com.example.internallearningtesttask.Constant.REQRES_GET_USERS;

@RestController
@RequestMapping("/api/externalHrSources")
public class ExternalHrSourceController {

    private final ExternalHrSourceService externalHrSourceService;

    public ExternalHrSourceController(ExternalHrSourceService externalHrSourceService) {
        this.externalHrSourceService = externalHrSourceService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ExternalHrSource>> uploadExternalHrSources() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        JsonNode response = restTemplate.getForObject(REQRES + REQRES_GET_USERS, JsonNode.class);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ExternalHrSource> externalHrSourceList = externalHrSourceService.uploadExternalHrSources(response);

        return new ResponseEntity<>(externalHrSourceList, HttpStatus.OK);
    }
}
