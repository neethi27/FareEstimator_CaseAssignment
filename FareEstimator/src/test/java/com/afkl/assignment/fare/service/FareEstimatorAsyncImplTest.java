package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.client.FareEstimatorClient;
import com.afkl.assignment.fare.domain.FareResponse;
import com.afkl.assignment.fare.service.impl.FareEstimatorAsyncServiceImpl;
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
@SpringBootTest(classes = FareEstimatorAsyncServiceImpl.class)
public class FareEstimatorAsyncImplTest {

    @InjectMocks
    FareEstimatorAsyncServiceImpl fareEstimatorAsyncService;

    @Mock
    FareEstimatorClient fareEstimatorClient;

    @Test
    public void testFindFareInfo() {
        ResponseEntity<FareResponse> fareResponse = Mockito.mock(ResponseEntity.class);
        when(fareEstimatorClient.getFareDetails(any(String.class),any(String.class))).thenReturn(fareResponse);
        CompletableFuture<FareResponse> completableFuture = fareEstimatorAsyncService.getFareInfo("AMS","JFK");
        assertNotNull(completableFuture);
    }
}

