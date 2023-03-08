package com.vominh.example.spring.batch.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vominh.example.spring.batch.dto.PopulationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Stack;

public class Reader implements ItemReader<PopulationDto> {

    private static final Logger log = LoggerFactory.getLogger(Reader.class);

    private Stack<PopulationDto> populationDtos;

    public Reader() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://datausa.io/api/data?drilldowns=Nation&measures=Population")).GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            JsonNode node = objectMapper.readTree(response.body());

            log.info(node.get("data").toString());
            populationDtos = objectMapper.readValue(node.get("data").toString(), new TypeReference<Stack<PopulationDto>>() {
            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public PopulationDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (populationDtos != null && !populationDtos.isEmpty()) {
            return populationDtos.pop();
        }

        return null;
    }
}
