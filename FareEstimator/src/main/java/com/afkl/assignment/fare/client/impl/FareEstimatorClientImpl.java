package com.afkl.assignment.fare.client.impl;

import java.util.HashMap;
import java.util.Map;

import com.afkl.assignment.fare.common.ExceptionHandlerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import com.afkl.assignment.fare.client.FareEstimatorClient;
import com.afkl.assignment.fare.domain.FareResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation class for fare estimator client
 * @author x087721
 */
@Component
@Slf4j
public class FareEstimatorClientImpl implements FareEstimatorClient {

    @Value("${afkl.api.faresUrl}")
    private String faresUrl;

    private final OAuth2RestTemplate restTemplate;

    private final ExceptionHandlerHelper exceptionHandlerHelper;

    /**
     * @param oAuth2RestTemplate
     */
    @Autowired
    public FareEstimatorClientImpl(OAuth2RestTemplate oAuth2RestTemplate,
                                   ExceptionHandlerHelper exceptionHandlerHelper ) {
        this.restTemplate = oAuth2RestTemplate;
        this.exceptionHandlerHelper = exceptionHandlerHelper;
    }

    /**
     * method to get fare details from mock api
     * @param origin
     * @param destination
     * @return futureResponse
     */
    @Override
    public ResponseEntity<FareResponse> getFareDetails(final String origin, final String destination) {
        log.info("method to call the mock api to get fare details");
        ResponseEntity<FareResponse> fareResponseEntity = null;
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("origin", origin);
        uriParams.put("destination", destination);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(faresUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            fareResponseEntity = restTemplate.exchange(builder.buildAndExpand(uriParams).toUriString(), HttpMethod.GET, requestEntity, FareResponse.class);
            if (!fareResponseEntity.getStatusCode().is2xxSuccessful()) {
                exceptionHandlerHelper.handleException(fareResponseEntity.getStatusCode());
            }
        }
        catch (ResourceAccessException | HttpStatusCodeException exception) {
            log.error("Exception occurred while calling mock api:{}", exception.getMessage(), exception);
            if (exception instanceof HttpStatusCodeException) {
                exceptionHandlerHelper.handleException(((HttpStatusCodeException) exception).getStatusCode());
            }
        }
        return fareResponseEntity;
    }
}
