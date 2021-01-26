package com.afkl.assignment.fare.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.domain.Metrics;
import com.afkl.assignment.fare.service.MetricsService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = MetricsController.class)
public class MetricsControllerTest {

    @InjectMocks
    MetricsController metricsController;

    @Mock
    MetricsService metricsService;

    @Test
    public void testGetStatistics() {
        Metrics metric = Metrics.builder().totalCount(1).build();
        when(metricsService.getHttpInfo()).thenReturn(metric);
        Metrics metrics  = metricsController.getStatistics();
        assertNotNull(metrics);
    }
}
