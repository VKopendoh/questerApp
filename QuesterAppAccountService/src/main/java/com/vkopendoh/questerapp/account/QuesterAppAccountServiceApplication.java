package com.vkopendoh.questerapp.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QuesterAppAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuesterAppAccountServiceApplication.class, args);
    }

}
