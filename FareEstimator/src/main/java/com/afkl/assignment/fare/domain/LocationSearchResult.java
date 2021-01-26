package com.afkl.assignment.fare.domain;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @author x087721
 *
 */
@Data
@ToString
public class LocationSearchResult {

    private List<Location> locations;

}