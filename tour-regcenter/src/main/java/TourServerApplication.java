package com.xmy.tourserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TourServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourServerApplication.class, args);
	}
}
