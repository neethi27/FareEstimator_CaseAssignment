package com.afkl.assignment.fare.client;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.afkl.assignment.fare.client.impl.AirportClientImpl;
import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AirportClientImpl.class)
public class AirportClientImplTest {

    @InjectMocks
    AirportClientImpl airportService;

    @Value("${afkl.api.airportUrl}")
    private String airportUrl;

    @Value("${afkl.api.airportsUrl}")
    private String airportsUrl;

    @Mock
    private OAuth2RestTemplate restTemplate;

    @Before
    public void setUp() {
        String airportUrl ="airport";
        ReflectionTestUtils.setField(airportService, "airportUrl", airportUrl);
        String airportsUrl ="airports";
        ReflectionTestUtils.setField(airportService, "airportsUrl", airportsUrl);
    }

    @Test
    public void testFindAirport() {
        Location location = new Location();
        ResponseEntity<Location> json = new ResponseEntity<>(
                        location,
                        HttpStatus.OK
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        when(restTemplate.exchange("airport", HttpMethod.GET, requestEntity, Location.class)).thenReturn(json);
        ResponseEntity<Location> locationSearchResultFuture = airportService.getAirportInfo("AMS");
        assertNotNull(locationSearchResultFuture);
    }

    @Test
    public void testFindAirports() {
        LocationSearchResponse location = new LocationSearchResponse();
        ResponseEntity<LocationSearchResponse> json = new ResponseEntity<>(
                        location,
                        HttpStatus.OK
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        when(restTemplate.exchange("airports", HttpMethod.GET, requestEntity, LocationSearchResponse.class)).thenReturn(json);
        ResponseEntity<LocationSearchResponse> locationSearchResultFuture = airportService.getAirportList();
        assertNotNull(locationSearchResultFuture);
    }

}
