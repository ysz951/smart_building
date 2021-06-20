package com.smart.building.smart_building_air_pressure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaClient
public class SmartBuildingAirPressureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBuildingAirPressureApplication.class, args);
	}

}
