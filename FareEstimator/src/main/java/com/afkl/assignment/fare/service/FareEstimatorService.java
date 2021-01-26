package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.domain.Fare;

/**
 * Interface to call fare estimator service implementation
 * 
 * @author x087721
 */
public interface FareEstimatorService {

    /**
     * method to get fare details from api
     * 
     * @param origin
     * @param destination
     * @return fare response
     */
    Fare findFare(final String origin, final String destination);

}
