package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.domain.Metrics;

/**
 * Interface to call metrics service implementation
 *
 * @author x087721
 */
public interface MetricsService {

    /**
     * @return metrics
     */
    Metrics getHttpInfo();
}
