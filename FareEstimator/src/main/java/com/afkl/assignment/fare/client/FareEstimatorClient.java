package com.afkl.assignment.fare.client;

import org.springframework.http.ResponseEntity;

import com.afkl.assignment.fare.domain.FareResponse;

/**
 * Interface to call fare estimator service client
 * @author x087721
 */
public interface FareEstimatorClient {

    /**
     * @param origin
     * @param destination
     * @return fareResponseFuture
     */
    ResponseEntity<FareResponse> getFareDetails(final String origin, final String destination);
}
