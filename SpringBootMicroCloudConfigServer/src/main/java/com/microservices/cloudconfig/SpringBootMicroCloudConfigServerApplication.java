package com.microservices.cloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringBootMicroCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroCloudConfigServerApplication.class, args);
	}
}
