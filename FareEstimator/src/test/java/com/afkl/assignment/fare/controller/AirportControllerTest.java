package com.afkl.assignment.fare.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.service.AirportService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AirportController.class)
public class AirportControllerTest {

    @InjectMocks
    AirportController airportController;

    @Mock
    AirportService airportService;

    @Test
    public void testFindAirport() {
        List<Airport> airportListMock = new ArrayList<>();
        when(airportService.getAirports()).thenReturn(airportListMock);
        List<Airport> airportList = airportController.getAirports();
        assertNotNull(airportList);
    }
}
