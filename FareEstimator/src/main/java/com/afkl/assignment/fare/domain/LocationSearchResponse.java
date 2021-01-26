package com.afkl.assignment.fare.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * @author x087721
 *
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchResponse {

    @JsonProperty("_embedded")
    private LocationSearchResult embedded;

}
