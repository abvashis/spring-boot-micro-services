if we want to connect limits-service to spring-cloud-config-server, application.properties need to be renamed as bootstrap.properties.

vm arguements or java application arguements can also be used to ser active profile other then properties file


default profile is dev and can be called by http://localhost:8080/limits

we can change spring.profiles.active=qa to execute qa properties file


if we have 2 instances of limits-service running, any changes committed to git will not be reflected untill we fire following post request for all instances:

localhost:8081/actuator/refresh   post request

localhost:8080/actuator/refresh   post request

response will be:
[
    "config.client.version",
    "limits-service.minimum"
]


If we have 100 instances then we have to fire refresh url 100 times, which is not good practive. Here Spring bus comes into picture

We will add followinf dependency in config and service app:
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bus-amqp</artifactId>
		</dependency>
		
and disable authentication on actuator in bootstrap.properties file:

management.endpoints.web.exposure.include=*

first start rabbit mq server in backend
Now we will hit post request localhost:8080/actuator/bus-refresh, which will refresh all instances of service on bus