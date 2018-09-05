package com.microservices.currencyconversion;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//name should be the service we are going to call, name should match in application.properties of currency-exchange-service
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient (name = "currency-exchange-service")
//commenting above  line mens we ask currency-conversion service to call zuul api gateway and not the exchange sevice
@FeignClient (name = "netflix-zuul-api-gateway-server")

//after adding ribbon on pom.xml, we need to enable ribbon on proxy
//once ribbon is enabled, we dont need url in feign client, since now we dont want to hardcode url rather configure in application.properties file

@RibbonClient(name = "currency-exchange-service")
public interface CurrencyServiceExchangeProxy {

	// in feign
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//commenting and replacing above line with below line means we are appending the name of application exposing the service
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValueJPA(@PathVariable("from") String from,
			@PathVariable("to") String to);

}
