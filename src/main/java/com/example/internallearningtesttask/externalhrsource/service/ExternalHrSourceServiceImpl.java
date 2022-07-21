package com.example.internallearningtesttask.externalhrsource.service;

import com.example.internallearningtesttask.externalhrsource.ExternalHrSource;
import com.example.internallearningtesttask.externalhrsource.dto.ExternalHrSourceDTO;
import com.example.internallearningtesttask.externalhrsource.exception.ExternalHrSourceParseException;
import com.example.internallearningtesttask.externalhrsource.repository.ExternalHrSourceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalHrSourceServiceImpl implements ExternalHrSourceService {

    private final ExternalHrSourceRepository externalHrSourceRepository;

    public ExternalHrSourceServiceImpl(ExternalHrSourceRepository externalHrSourceRepository) {
        this.externalHrSourceRepository = externalHrSourceRepository;
    }

    @Override
    @Transactional
    public List<ExternalHrSource> uploadExternalHrSources(JsonNode jsonResponse) {
        if (jsonResponse == null) {
            return Collections.emptyList();
        }

        List<ExternalHrSource> externalHrSourceList = parseResponse(jsonResponse);
        if (externalHrSourceList.isEmpty()) {
            return Collections.emptyList();
        }

        return externalHrSourceRepository.saveAll(
                externalHrSourceList.stream()
                .filter(externalHrSource -> !externalHrSourceRepository.existsByEmail(externalHrSource.getEmail()))
                .toList()
        );
    }

    private List<ExternalHrSource> parseResponse(JsonNode jsonResponse) {
        JsonNode data = jsonResponse.get("data");
        ObjectMapper mapper = new ObjectMapper();
        ModelMapper modelMapper = new ModelMapper();

        ExternalHrSourceDTO[] externalHrSourceDTOS;
        try {
            externalHrSourceDTOS = mapper.treeToValue(data, ExternalHrSourceDTO[].class);
        } catch (JsonProcessingException e) {
            throw new ExternalHrSourceParseException("Cannot parse the response due to " + e.getMessage());
        }

        return Arrays.stream(externalHrSourceDTOS)
                .map(dto -> modelMapper.map(dto, ExternalHrSource.class))
                .toList();
    }
}
