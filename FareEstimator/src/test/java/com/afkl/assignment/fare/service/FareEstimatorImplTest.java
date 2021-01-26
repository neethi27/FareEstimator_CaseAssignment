package com.afkl.assignment.fare.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.afkl.assignment.fare.domain.Fare;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.domain.FareResponse;
import com.afkl.assignment.fare.service.impl.FareEstimatorImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FareEstimatorImpl.class)
public class FareEstimatorImplTest {

    @InjectMocks
    FareEstimatorImpl fareEstimator;

    @Mock
    AirportService airportService;

    @Mock
    FareEstimatorAsyncService fareEstimatorAsyncService;

    @Test
    public void testGetFare() throws ExecutionException, InterruptedException {
        when(airportService.findAirport(any(String.class))).thenReturn(Airport.builder().code("AMS").build());
        CompletableFuture<FareResponse> mockedFuture = Mockito.mock(CompletableFuture.class);
        FareResponse fareResponse = new FareResponse();
        when(mockedFuture.get()).thenReturn(fareResponse);
        when(fareEstimatorAsyncService.getFareInfo(any(String.class),any(String.class))).thenReturn(mockedFuture);
        Fare fare = fareEstimator.findFare("AMS","AMS");
        assertNotNull(fare);
    }
}
