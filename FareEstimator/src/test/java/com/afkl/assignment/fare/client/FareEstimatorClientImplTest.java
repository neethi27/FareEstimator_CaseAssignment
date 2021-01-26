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

import com.afkl.assignment.fare.client.impl.FareEstimatorClientImpl;
import com.afkl.assignment.fare.domain.FareResponse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FareEstimatorClientImpl.class)
public class FareEstimatorClientImplTest {

    @InjectMocks
    FareEstimatorClientImpl fareEstimator;

    @Value("${afkl.api.faresUrl}")
    private String faresUrl;

    @Mock
    OAuth2RestTemplate restTemplate;

    @Before
    public void setUp() {
        String faresUrl ="fares/AMS/JFK";
        ReflectionTestUtils.setField(fareEstimator, "faresUrl", faresUrl);
    }

    @Test
    public void testFindFare(){
        FareResponse fare =  new FareResponse();
        ResponseEntity<FareResponse> json = new ResponseEntity<>(
                        fare,
                        HttpStatus.OK
        );
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        when(restTemplate.exchange("fares/AMS/JFK", HttpMethod.GET, requestEntity, FareResponse.class)).thenReturn(json);
        ResponseEntity<FareResponse> fareResponseFuture = fareEstimator.getFareDetails("AMS","JFK");
        assertNotNull(fareResponseFuture);
    }
}

