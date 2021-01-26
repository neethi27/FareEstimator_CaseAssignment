package com.afkl.assignment.fare.common;

import com.afkl.assignment.fare.domain.Metrics;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * utility class for calculating metrics
 * @author x087721
 */
@Component
public class MetricsUtility {

    int status2xxCount, status4xxCount, status5xxCount, totalCount = 0;
    long maxTime, minTime = 0;

    /**
     * method to calculate metrics
     * @param trace
     * @return
     */
    public Metrics findMetrics(List<HttpTrace> trace){
        trace.forEach(this::mapMetrics);
        return  Metrics.builder()
                        .totalCount(totalCount)
                        .statusOKCount(status2xxCount)
                        .status4XXCount(status4xxCount)
                        .status5XXCount(status5xxCount)
                        .maxTime(toIntExact(maxTime))
                        .minTime(toIntExact(minTime))
                        .build();
    }

    /**
     * method iterate get request count
     * @param t
     */
    private void mapMetrics(HttpTrace t) {
        if (!t.getRequest().getUri().getPath().contains("/statistics")) {
            totalCount++;
            int status = t.getResponse().getStatus();
            if (status >= 200 && status < 300)
                status2xxCount++;
            else if (status >= 400 && status < 500)
                status4xxCount++;
            else if (status >= 500)
                status5xxCount++;

            if (t.getTimeTaken() > maxTime)
                maxTime = t.getTimeTaken();

            if (totalCount == 1 || t.getTimeTaken() < minTime)
                minTime = t.getTimeTaken();
        }
    }
}
