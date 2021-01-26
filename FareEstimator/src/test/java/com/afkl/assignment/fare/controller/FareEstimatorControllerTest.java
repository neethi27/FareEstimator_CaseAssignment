package com.afkl.assignment.fare.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.afkl.assignment.fare.domain.Fare;
import com.afkl.assignment.fare.service.FareEstimatorService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FareEstimatorController.class)
public class FareEstimatorControllerTest {

    @InjectMocks
    FareEstimatorController fareEstimatorController;

    @Mock
    FareEstimatorService fareEstimatorService;

    @Test
    public void testFindFare() {
        Fare mockedFare = Fare.builder().destination("AMS").build();
        when(fareEstimatorService.findFare(any(String.class),any(String.class))).thenReturn(mockedFare);
        Fare fare = fareEstimatorController.findFare("AMS","AMS");
        assertNotNull(fare);
    }

}
