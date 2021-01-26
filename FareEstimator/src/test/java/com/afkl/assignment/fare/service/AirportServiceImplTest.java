package com.afkl.assignment.fare.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.domain.Airport;
import com.afkl.assignment.fare.domain.Location;
import com.afkl.assignment.fare.domain.LocationSearchResponse;
import com.afkl.assignment.fare.domain.LocationSearchResult;
import com.afkl.assignment.fare.service.impl.AirportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AirportServiceImpl.class)
public class AirportServiceImplTest {

    @InjectMocks
    AirportServiceImpl airportService;

    @Mock
    AirportAsyncService airportClient;

    @Test
    public void testGetAirport() throws InterruptedException, ExecutionException {
        CompletableFuture<Location> mockedFuture = Mockito.mock(CompletableFuture.class);
        when(mockedFuture.get()).thenReturn(getLocation());

        when(airportClient.getAirportInfo(any(String.class))).thenReturn(mockedFuture);
        Airport airport =  airportService.findAirport("AMS");
        assertNotNull(airport);
    }

    @Test
    public void testGetAirports() throws InterruptedException, ExecutionException {
        CompletableFuture<LocationSearchResponse> mockedFuture = Mockito.mock(CompletableFuture.class);
        when(mockedFuture.get()).thenReturn(getLocationResponseFuture());

        when(airportClient.getAirportList()).thenReturn(mockedFuture);
        List<Airport> airport =  airportService.getAirports();
        assertNotNull(airport);
    }


    private Location getLocation() {
        Location location = new Location();
        location.setCode("AMS");
        location.setDescription("Amsterdam Schipol");
        location.setParent(location);
        return location;
    }

    private LocationSearchResponse getLocationResponseFuture() {
        LocationSearchResponse locationSearchResponse = new LocationSearchResponse();
        LocationSearchResult locationResponseFuture = new LocationSearchResult();
        Location location = new Location();
        location.setCode("AMS");
        location.setDescription("Amsterdam Schipol");
        location.setParent(location);
        List<Location> locList = new ArrayList<>();
        locList.add(location);
        locationResponseFuture.setLocations(locList);
        locationSearchResponse.setEmbedded(locationResponseFuture);
        return locationSearchResponse;
    }
}
