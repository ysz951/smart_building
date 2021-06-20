package com.smart.building.smart_building_room_meeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
public class SmartBuildingRoomMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBuildingRoomMeetingApplication.class, args);
	}

}
