package com.afkl.assignment.fare.service.impl;

import com.afkl.assignment.fare.client.FareEstimatorClient;
import com.afkl.assignment.fare.domain.FareResponse;
import com.afkl.assignment.fare.service.FareEstimatorAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Service call to get fare info from api asynchronous
 * @author x087721
 */
@Service
@Slf4j
public class FareEstimatorAsyncServiceImpl implements FareEstimatorAsyncService {

    private final FareEstimatorClient fareEstimatorClient;

    /**
     * @param fareEstimatorClient
     */
    @Autowired
    public FareEstimatorAsyncServiceImpl(FareEstimatorClient fareEstimatorClient){
        this.fareEstimatorClient = fareEstimatorClient;
    }

    /**
     * method to get fare details with async call
     * @param origin
     * @param destination
     * @return
     */
    @Async("asyncPool")
    @Override
    public CompletableFuture<FareResponse> getFareInfo(String origin, String destination) {
        ResponseEntity<FareResponse> responseEntity = fareEstimatorClient.getFareDetails(origin,destination);
        return CompletableFuture.completedFuture(responseEntity.getBody());
    }
}
