package com.afkl.assignment.fare.service.impl;

import com.afkl.assignment.fare.common.MetricsUtility;
import com.afkl.assignment.fare.domain.Metrics;
import com.afkl.assignment.fare.service.MetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation class for metrics
 * @author x087721
 */
@Service
@Slf4j
public class MetricsServiceImpl implements MetricsService {


    private final HttpTraceRepository httpTraceRepository;

    @Autowired
    public MetricsServiceImpl(HttpTraceRepository httpTraceRepository){
        this.httpTraceRepository = httpTraceRepository;
    }

    /**
     * method to get metrics
     * @return metrics
     */
    @Override
    public Metrics getHttpInfo() {
        log.info("method to get http request info from actuator");
        MetricsUtility metricsUtility = new MetricsUtility();
        Metrics metrics = null;
        List<HttpTrace> trace = httpTraceRepository.findAll();
        if (trace != null && !trace.isEmpty()) {
            metrics = metricsUtility.findMetrics(trace);
        }
        return metrics;
    }
}
