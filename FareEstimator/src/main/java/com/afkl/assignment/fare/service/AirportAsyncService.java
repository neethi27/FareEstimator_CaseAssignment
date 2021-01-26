package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Interface to call airport service asynchronous
 * @author x087721
 */
public interface AirportAsyncService {

    /**
     * method to call mock api asynchronous
     * @param airportCode
     * @return locationResponse
     */
    CompletableFuture<Location> getAirportInfo(String airportCode);

    /**
     * @return locationResponse
     */
    CompletableFuture<LocationSearchResponse> getAirportList();
}
