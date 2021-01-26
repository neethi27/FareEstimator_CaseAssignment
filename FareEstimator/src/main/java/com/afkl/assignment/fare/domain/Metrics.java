package com.afkl.assignment.fare.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author x087721
 */
@Data
@Builder
public class Metrics {

    private int maxTime;
    private int minTime;
    private int totalCount;
    private int statusOKCount;
    private int status4XXCount;
    private int status5XXCount;
}
