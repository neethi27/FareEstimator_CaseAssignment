package com.afkl.assignment.fare.service.impl;

import com.afkl.assignment.fare.client.AirportClient;
import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;
import com.afkl.assignment.fare.service.AirportAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Service impln class to make the async call to mock api
 */
@Service
public class AirportAsyncServiceImpl implements AirportAsyncService {

    private final AirportClient airportClient;

    /**
     * @param airportClient
     */
    @Autowired
    public AirportAsyncServiceImpl(AirportClient airportClient){
        this.airportClient = airportClient;
    }

    /**
     * @param airportCode
     * @return completable future response
     */
    @Async("asyncPool")
    @Override
    public CompletableFuture<Location> getAirportInfo(String airportCode) {
        ResponseEntity<Location> responseEntity = airportClient.getAirportInfo(airportCode);
        return CompletableFuture.completedFuture(responseEntity.getBody());
    }

    /**
     * method to get airport list
     * @return completable future response
     */
    @Async("asyncPool")
    @Override
    public CompletableFuture<LocationSearchResponse> getAirportList() {
        ResponseEntity<LocationSearchResponse> responseEntity = airportClient.getAirportList();
        return CompletableFuture.completedFuture(responseEntity.getBody());
    }
}
