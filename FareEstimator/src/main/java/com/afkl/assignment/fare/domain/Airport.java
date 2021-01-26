package com.afkl.assignment.fare.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author x087721
 *
 */
@Data
@Builder
public class Airport {
	
	private String code;
	private String name;
	private String country;
}
