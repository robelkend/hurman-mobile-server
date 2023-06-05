package com.rsoft.hurmanmobileapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients({"com.rsoft.hurmanmobileapp"})
@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableAsync
public class HurmanMobileAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HurmanMobileAppApplication.class, args);
	}

}
