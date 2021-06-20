package com.smart.building.smart_building_rabbitmq_publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmartBuildingRabbitmqPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBuildingRabbitmqPublisherApplication.class, args);
	}

}
