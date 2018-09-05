package com.boot.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.microservices.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitFromConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	
	//using hystrix fault tolerance we define a fallback method, so that we can define a default behaviour if any of the micro service is failed
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallBackRetrieveConfiguration")
	public LimitConfiguration retrieveConfig() {
		throw new RuntimeException();
	}
	
	public LimitConfiguration fallBackRetrieveConfiguration() {
		return new LimitConfiguration(999,9) ;
	}

}
