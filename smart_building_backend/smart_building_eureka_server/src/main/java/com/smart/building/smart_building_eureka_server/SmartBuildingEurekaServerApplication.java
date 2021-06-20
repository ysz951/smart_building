package com.smart.building.smart_building_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SmartBuildingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBuildingEurekaServerApplication.class, args);
	}

}
