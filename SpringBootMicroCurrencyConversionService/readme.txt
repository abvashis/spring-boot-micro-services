steps to register this application with naming server are:

1-> add dependency in pom.xml:
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
		
2->Add annotation @EnableDiscoveryClient in main class

3->Add eureka server url in application.properties file:

eureka.client.service-url.default-zone=http://localhost:8761/eureka		

Further when the currency conversion service wants to use currency exchange service, we want it to use naming
server to get the details of currency exchange service.
So, instead of hardcoding url's for ribbon, we would like Ribbon to talk to naming server to get al instances of services running

To do this we only need to disable following line in application.properties and Ribbon will start talking to eureka

currency-exchange-service.ribbon.listOfServers=http://localhost:8000/,http://localhost:8001
