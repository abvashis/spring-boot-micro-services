steps to register this application with naming server are:

1-> add dependency in pom.xml:
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
		
2->Add annotation @EnableDiscoveryClient in main class

3->Add eureka server url in application.properties file:

eureka.client.service-url.default-zone=http://localhost:8761/eureka		