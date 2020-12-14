package com.vkopendoh.questerapp.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QuesterAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesterAppDiscoveryServiceApplication.class, args);
	}

}
