package com.afkl.assignment.fare.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author x087721
 *
 */
@Data
@ToString
public class FareResponse {

    private double amount;
    private String currency;
    private String origin, destination;

}
