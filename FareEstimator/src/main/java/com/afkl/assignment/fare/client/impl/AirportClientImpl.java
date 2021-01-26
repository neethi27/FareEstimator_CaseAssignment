package com.afkl.assignment.fare.client.impl;

import java.util.HashMap;
import java.util.Map;

import com.afkl.assignment.fare.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import com.afkl.assignment.fare.client.AirportClient;
import com.afkl.assignment.fare.common.ExceptionHandlerHelper;
import com.afkl.assignment.fare.domain.LocationSearchResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation to call mock api
 *
 * @author x087721
 */
@Component
@Slf4j
public class AirportClientImpl implements AirportClient {

    @Value("${afkl.api.airportsUrl}")
    private String airportsUrl;

    @Value("${afkl.api.airportUrl}")
    private String airportUrl;

    private final OAuth2RestTemplate restTemplate;

    private final ExceptionHandlerHelper exceptionHandlerHelper;

    /**
     * @param oAuth2RestTemplate
     */
    @Autowired
    public AirportClientImpl(OAuth2RestTemplate oAuth2RestTemplate,
                             ExceptionHandlerHelper exceptionHandlerHelper) {
        this.exceptionHandlerHelper = exceptionHandlerHelper;
        this.restTemplate = oAuth2RestTemplate;
    }

    /**
     * method to call mock api asynchronous to get airport information
     * @param airportCode
     * @return responseEntity
     */
    @Override
    public ResponseEntity<Location> getAirportInfo(String airportCode) {
        log.info("method to call mock api");
        ResponseEntity<Location> location = null;
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("code", airportCode);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportUrl);

        try {
            location = restTemplate.exchange(builder.buildAndExpand(uriParams).toUriString(), HttpMethod.GET, getEntity(), Location.class);
            if(!location.getStatusCode().is2xxSuccessful()){
                exceptionHandlerHelper.handleException(location.getStatusCode());
            }
        }
        catch (ResourceAccessException | HttpStatusCodeException exception) {
            log.error("exception occurred while calling the mock service:{}", exception.getMessage(), exception);
            if(exception instanceof HttpStatusCodeException){
                exceptionHandlerHelper.handleException(((HttpStatusCodeException) exception).getStatusCode());
            }
        }
        return location;
    }

    /**
     * method to call mock api asynchronous to get airport list
     * @return responseEntity
     */
    @Override
    public ResponseEntity<LocationSearchResponse> getAirportList() {
        log.info("method to call mock api to get airport list");
        ResponseEntity<LocationSearchResponse> locationSearchResponse = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportsUrl);

        try {
            locationSearchResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, getEntity(), LocationSearchResponse.class);
            if(!locationSearchResponse.getStatusCode().is2xxSuccessful()){
                exceptionHandlerHelper.handleException(locationSearchResponse.getStatusCode());
            }
        }
        catch (ResourceAccessException | HttpStatusCodeException exception) {
            log.error("exception occurred while calling the mock service:{}", exception.getMessage(), exception);
            if(exception instanceof HttpStatusCodeException){
                exceptionHandlerHelper.handleException(((HttpStatusCodeException) exception).getStatusCode());
            }
        }
        return locationSearchResponse;
    }

    /**
     * @return
     */
    private HttpEntity<String> getEntity() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeader);
    }
}
