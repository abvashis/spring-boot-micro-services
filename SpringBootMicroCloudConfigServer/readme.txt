Setting up local git repo:

git init
Initialized empty Git repository in /Users/abhishekvashistha/spring-cloud-git-localconfig-repo/.git/

//Add git folder to eclipse project using Build Path --> Link Source->Browse to local git repo
//create a file as limits-service.properties in new git folder now visible in eclipse project


AbhishekMBP:spring-cloud-git-localconfig-repo abhishekvashistha$ ls
limits-service.properties
AbhishekMBP:spring-cloud-git-localconfig-repo abhishekvashistha$ git add -A
AbhishekMBP:spring-cloud-git-localconfig-repo abhishekvashistha$ git commit -m "first comment for limits-service.properties"
[master (root-commit) 732b390] first comment for limits-service.properties
 1 file changed, 2 insertions(+)
 create mode 100644 limits-service.properties
 
 
 
 configuration of rabbit mq server and connecting zipkin to rabbitmq:
 
 start rabbit mq using: /usr/local/sbin/rabbitmq-server 
 start zipkin using: ha$ RABBIT_URI=amqp://localhost java -jar /Users/abhishekvashistha/Documents/workspace-sts-3.9.4.RELEASE/spring-boot-microservices-jar/zipkin-server-2.9.4-exec.jar 

 Next step will be to enable services to put messages on to Rabbitmq queue:
 
 following dependencies are required:
 
 <!-- sleuth zipkin tells the format in which message is expected -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-sleuth-zipkin</artifactId>
		</dependency>

		<!-- bus-ampq tells the protocol to be used, default amqp is rabbitmq -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bus-amqp</artifactId>
		</dependency>