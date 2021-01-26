package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.client.AirportClient;
import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;
import com.afkl.assignment.fare.service.impl.AirportAsyncServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AirportAsyncServiceImpl.class)
public class AirportAsyncServiceImplTest {

    @InjectMocks
    AirportAsyncServiceImpl airportAsyncService;

    @Mock AirportClient airportClient;

    @Test
    public void testGetAirport() {
        ResponseEntity<Location> responseEntity = Mockito.mock(ResponseEntity.class);
        when(airportClient.getAirportInfo(any(String.class))).thenReturn(responseEntity);
        CompletableFuture<Location> responseCompletableFuture = airportAsyncService.getAirportInfo("AMS");
        assertNotNull(responseCompletableFuture);
    }

    @Test
    public void testGetAirports() {
        ResponseEntity<LocationSearchResponse> responseEntity = Mockito.mock(ResponseEntity.class);
        when(airportClient.getAirportList()).thenReturn(responseEntity);
        CompletableFuture<LocationSearchResponse> responseCompletableFuture = airportAsyncService.getAirportList();
        assertNotNull(responseCompletableFuture);
    }
}
