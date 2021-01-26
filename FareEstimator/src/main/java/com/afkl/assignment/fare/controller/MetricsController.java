package com.afkl.assignment.fare.controller;

import com.afkl.assignment.fare.domain.Metrics;
import com.afkl.assignment.fare.service.MetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to get metrics info
 * @author x087721
 */
@RestController
@Slf4j
public class MetricsController {

    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    /**
     * method to get statistics
     * @return
     */
    @GetMapping("/statistics")
    public Metrics getStatistics() {
        return metricsService.getHttpInfo();
    }
}
