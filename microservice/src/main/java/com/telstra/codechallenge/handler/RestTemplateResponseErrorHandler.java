package com.telstra.codechallenge.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.exception.RestTemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
                ObjectMapper mapper = new ObjectMapper();
                String errorMessage = "";
                try {
                    JsonNode node = mapper.readTree(httpBodyResponse);
                    errorMessage = node.get("message").asText();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                throw new RestTemplateException(httpResponse.getStatusCode(), errorMessage);
            }
        }
    }
}