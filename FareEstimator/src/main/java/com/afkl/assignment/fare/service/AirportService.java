package com.afkl.assignment.fare.service;

import java.util.List;

import com.afkl.assignment.fare.domain.Airport;

/**
 * Interface to call airport service implementation
 * @author x087721
 */
public interface AirportService {

    /**
     * @return airport list
     *
     */
    List<Airport> getAirports();

    /**
     * @param airportCode
     * @return airport
     */
    Airport findAirport(String airportCode);
}
