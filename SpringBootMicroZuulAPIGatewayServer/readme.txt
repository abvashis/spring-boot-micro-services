to pass any request from zool api server we follow rules as:

{zool_api_base_url}/{application_name}/{request_uri}

so for intercepting currency exchange uri new request will be:
http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR


and for intercepting currency conversion uri new request will be:

http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/100

above url will hit zuul api gateway twice once for conversion and next for exchange service



************************************************************************************************************************************************************

we will add spring slouth, wich adds a unique id to every request url in following services:
currency-exchange
currency-conversion
zuul-api-gateway

there are 2 steps to add sleuth (distributed tracking system) to a service:

first is to add following dependency in pom.xml:
		<dependency>
          	<groupId>org.springframework.cloud</groupId>
        	<artifactId>spring-cloud-starter-sleuth</artifactId>
   		 </dependency>
		
second, to the application main class create a bean	Sampler which will tell what all requests to intercept:

@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	on hitting 'http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000', 
	we get logs in console for every application with a id:
	
	2018-08-03 16:06:37.458  INFO [currency-exchange-service,99a43a23e25f76a8,3da7a02bfff9408b,true] 2946 --- [nio-8000-exec-8] c.m.c.CurrencyExchangeController         : com.microservices.currencyexchange.ExchangeValue@319477da
	
	2018-08-03 16:06:34.945  INFO [currency-conversion-service,,,] 2992 --- [trap-executor-0] c.n.d.s.r.aws.ConfigClusterResolver      : Resolving eureka endpoints via configuration
	2018-08-03 16:06:37.462  INFO [currency-conversion-service,99a43a23e25f76a8,99a43a23e25f76a8,true] 2992 --- [nio-8100-exec-7] c.m.c.CurrencyConversionController       : com.microservices.currencyconversion.CurrencyConversionBean@33d63697
	
	
	2018-08-03 16:06:37.449  INFO [netflix-zuul-api-gateway-server,99a43a23e25f76a8,5cfb56b6591a4e93,true] 3003 --- [nio-8765-exec-5] c.m.z.ZuulLogingFilter                   : request -> org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@55ada381 request uri   ->/currency-exchange-service/currency-exchange/from/EUR/to/INR
	2018-08-03 16:06:37.449  INFO [netflix-zuul-api-gateway-server,99a43a23e25f76a8,5cfb56b6591a4e93,true] 3003 --- [nio-8765-exec-5] c.m.z.ZuulLogingFilter                   : request -> org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@55ada381 context path  ->
	2018-08-03 16:06:37.449  INFO [netflix-zuul-api-gateway-server,99a43a23e25f76a8,5cfb56b6591a4e93,true] 3003 --- [nio-8765-exec-5] c.m.z.ZuulLogingFilter                   : request -> org.springframework.cloud.netflix.zuul.filters.pre.Servlet30RequestWrapper@55ada381 server name  ->abhishekmbp
	2018-08-03 16:06:37.449  INFO [netflix-zuul-api-gateway-server,99a43a23e25f76a8,5cfb56b6591a4e93,true] 3003 --- [nio-8765-exec-5] c.m.z.ZuulLogingFilter  
	
	however, these logs are distributed and thus comes the need of distributed tracing server comes in we use zipkin to do this
		