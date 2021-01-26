package com.afkl.assignment.fare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Fare Estimator main class to start the spring boot application
 * @author x087721
 *
 */
@SpringBootApplication
public class FareEstimatorApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(com.afkl.assignment.fare.FareEstimatorApplication.class, args);
	}


	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
