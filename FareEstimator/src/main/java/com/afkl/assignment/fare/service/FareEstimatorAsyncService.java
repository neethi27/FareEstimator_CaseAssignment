package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.domain.FareResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Interface to call fare estimator service async
 * @author x087721
 */
public interface FareEstimatorAsyncService {

    /**
     * @param origin
     * @param destination
     * @return
     */
    CompletableFuture<FareResponse> getFareInfo(final String origin, final String destination);
}
