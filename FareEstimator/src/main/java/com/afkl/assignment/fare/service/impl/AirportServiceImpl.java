package com.afkl.assignment.fare.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;
import com.afkl.assignment.fare.service.AirportAsyncService;
import com.afkl.assignment.fare.service.AirportService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation class for airport feature
 * 
 * @author x087721
 */
@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

    private final AirportAsyncService airportAsyncService;

    @Autowired
    public AirportServiceImpl(AirportAsyncService airportAsyncService) {
        this.airportAsyncService = airportAsyncService;
    }

    /**
     * method to get airport list from mock api
     *
     * @return airport list
     */
    @Override
    public List<Airport> getAirports() {
        log.info("service method to get airport list from mock api");
        CompletableFuture<LocationSearchResponse> searchResponseFuture = airportAsyncService.getAirportList();
        LocationSearchResponse locationSearchResponse = null;
        try {
            locationSearchResponse = searchResponseFuture.get();
        }
        catch (InterruptedException | ExecutionException exp) {
            log.info("exception occurred while getting future response : {}", exp.getMessage(), exp);
        }
        if(locationSearchResponse != null){
            List<Location> locations = locationSearchResponse.getEmbedded().getLocations();
            return locations.stream()
                            .map(this::buildAirport)
                            .collect(Collectors.toList());
        }else{
            return null;
        }
    }

    /**
     * method to get airport info from api
     * @param airportCode
     * @return airport
     */
    @Override
    public Airport findAirport(String airportCode) {
        log.info("service method to get airport info from mock api for airport :{}",airportCode);
        CompletableFuture<Location> searchResponseFuture = airportAsyncService.getAirportInfo(airportCode);
        Location location = null;
        try {
            location = searchResponseFuture.get();
        }
        catch (InterruptedException | ExecutionException exp) {
            log.info("exception occurred while getting future response : {}", exp.getMessage(), exp);
        }

        if(location != null){
            return buildAirport(location);
        } else{
            return null;
        }
    }

    /**
     * airport builder method
     * @param location
     * @return airport
     */
    private Airport buildAirport(Location location) {
        return Airport.builder()
                        .code(location.getCode())
                        .country(location.getParent().getName())
                        .name(location.getName())
                        .build();
    }
}
