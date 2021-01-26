package com.afkl.assignment.fare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.service.AirportService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller class to handle airport info requests
 * @author x087721
 */
@RestController
@Slf4j
public class AirportController {

    private final AirportService airportService;

    /**
     * @param airportService
     */
    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * method to get list of airports
     * @return list of Airports
     *
     */
    @GetMapping("/airports")
    public List<Airport> getAirports() {
        log.info("to get airport info");
        return airportService.getAirports();
    }

}
