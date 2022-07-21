package com.example.internallearningtesttask.externalhrsource.service;

import com.example.internallearningtesttask.externalhrsource.ExternalHrSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface ExternalHrSourceService {
    List<ExternalHrSource> uploadExternalHrSources(JsonNode jsonResponse) throws JsonProcessingException;
}
