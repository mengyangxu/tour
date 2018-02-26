package com.xmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TourServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourServiceApplication.class, args);
	}
}
