package com.microservices.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("com.microservices.currencyconversion")
// Scans for interfaces that declare they are feign clients (via @FeignClient).
// Configures component scanning directives for use with @Configuration classes.
@EnableDiscoveryClient
public class SpringBootMicroCurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroCurrencyConversionServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
