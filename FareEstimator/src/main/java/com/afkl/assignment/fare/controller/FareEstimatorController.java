package com.afkl.assignment.fare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.assignment.fare.domain.Fare;
import com.afkl.assignment.fare.service.FareEstimatorService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller class to handle fare estimation requests
 * @author x087721
 */
@RestController
@Slf4j
public class FareEstimatorController {

    private final FareEstimatorService fareEstimatorService;
    
    /**
     * @param fareEstimatorService
     */
    @Autowired
    public FareEstimatorController(FareEstimatorService fareEstimatorService) {
        this.fareEstimatorService = fareEstimatorService;
    }

    /**
     * method to get fare between the flight segment
     * @param origin
     * @param destination
     * @return fare
     */
    @GetMapping("/getFare")
    public Fare findFare(@RequestParam("origin")final String origin,
                         @RequestParam("destination") final String destination) {
        log.info("retrieving fare for the flight segment:{} and :{}", origin, destination);
        return fareEstimatorService.findFare(origin, destination);
    }

}
