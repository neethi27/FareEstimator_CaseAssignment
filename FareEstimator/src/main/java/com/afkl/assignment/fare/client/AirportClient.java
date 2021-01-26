package com.afkl.assignment.fare.client;

import com.afkl.assignment.fare.domain.Location;
import org.springframework.http.ResponseEntity;

import com.afkl.assignment.fare.domain.LocationSearchResponse;

/**
 * Interface to call airport service client
 * @author x087721
 */
public interface AirportClient {

    /**
     * @param airportCode
     * @return locationSearchResponse
     */
    ResponseEntity<Location> getAirportInfo(String airportCode);

    /**
     * @return locationSearchResponse
     */
    ResponseEntity<LocationSearchResponse> getAirportList();

}
