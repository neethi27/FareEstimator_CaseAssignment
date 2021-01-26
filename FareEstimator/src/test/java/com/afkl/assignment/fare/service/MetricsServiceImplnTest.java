package com.afkl.assignment.fare.service;

import com.afkl.assignment.fare.domain.Metrics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.service.impl.MetricsServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = MetricsServiceImpl.class)
public class MetricsServiceImplnTest {

    @InjectMocks
    MetricsServiceImpl metricsService;

    @Mock
    HttpTraceRepository httpTraceRepository;

    @Test
    public void testGetHttpInfo() {
        List<HttpTrace> trace = new ArrayList<>();
        when(httpTraceRepository.findAll()).thenReturn(trace);
        Metrics metrics = metricsService.getHttpInfo();
        assertNull(metrics);
    }
}
