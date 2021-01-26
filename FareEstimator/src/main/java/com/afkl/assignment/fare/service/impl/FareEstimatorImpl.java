package com.afkl.assignment.fare.service.impl;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.domain.Fare;
import com.afkl.assignment.fare.domain.FareResponse;
import com.afkl.assignment.fare.service.AirportService;
import com.afkl.assignment.fare.service.FareEstimatorAsyncService;
import com.afkl.assignment.fare.service.FareEstimatorService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation class for fare estimator feature
 * @author x087721
 */
@Service
@Slf4j
public class FareEstimatorImpl implements FareEstimatorService {

    private final FareEstimatorAsyncService fareEstimatorAsyncService;

    private final AirportService airportService;

    /**
     * @param fareEstimatorAsyncService
     * @param airportService
     */
    @Autowired
    public FareEstimatorImpl(FareEstimatorAsyncService fareEstimatorAsyncService,
                             AirportService airportService) {
        this.fareEstimatorAsyncService = fareEstimatorAsyncService;
        this.airportService = airportService;
    }


    /**
     * call service to get fare details for the segment
     * @param origin,destination
     * @return fare response
     * @throws IOException
     */
    @Override
    public Fare findFare(final String origin,
                         final String destination){
        log.info("Finding fare between airports: {} and {}", origin, destination);
        Fare fare = null;
        Airport originLocation = airportService.findAirport(origin);
        Airport destinationLocation = airportService.findAirport(destination);

        //check if service returned valid origin and destination before getting fare info
        if(originLocation == null || destinationLocation == null) {
            return null;
        }

        CompletableFuture<FareResponse> fareResponseFuture = fareEstimatorAsyncService.getFareInfo(origin, destination);
        FareResponse fareResponse = null;
        try {
            fareResponse = fareResponseFuture.get();
        }
        catch (InterruptedException | ExecutionException exp) {
            log.info("exception occurred while getting future response : {}", exp.getMessage(), exp);
        }
        if(fareResponse != null){
            fare = Fare.builder()
                        .amount(fareResponse.getAmount())
                        .currency(fareResponse.getCurrency())
                        .destination(fareResponse.getDestination())
                        .origin(fareResponse.getOrigin())
                        .build();
        }
        return fare;
    }
}
