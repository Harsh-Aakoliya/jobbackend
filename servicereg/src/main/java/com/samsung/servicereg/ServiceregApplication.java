package com.samsung.servicereg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
public class ServiceregApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceregApplication.class, args);
	}

}
