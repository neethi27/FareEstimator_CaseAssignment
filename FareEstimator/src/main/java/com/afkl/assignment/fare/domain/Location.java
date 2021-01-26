package com.afkl.assignment.fare.domain;

import java.util.Set;

import lombok.Data;

/**
 * @author x087721
 *
 */
@Data
public class Location {

    private String code, name, description;
    private Coordinates coordinates;
    private Location parent;
    private Set<Location> children;

}

