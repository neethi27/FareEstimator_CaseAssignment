package com.afkl.assignment.fare.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author x087721
 *
 */
@Data
@Builder
@ToString
public class Fare {

    private double amount;
    private String currency;
    private String origin, destination;

}

