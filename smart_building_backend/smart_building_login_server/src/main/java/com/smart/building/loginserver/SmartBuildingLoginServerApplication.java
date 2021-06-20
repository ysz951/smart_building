package com.smart.building.loginserver;

import com.smart.building.loginserver.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableConfigurationProperties(AppProperties.class)
public class SmartBuildingLoginServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartBuildingLoginServerApplication.class, args);
	}

}
