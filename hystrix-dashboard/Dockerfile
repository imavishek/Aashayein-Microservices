# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8053 available to the world outside this container
EXPOSE 8053

# The application's jar file
ARG JAR_FILE=target/hystrix-dashboard.jar

# Add the application's jar to the container
ADD ${JAR_FILE} hystrix-dashboard.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/hystrix-dashboard.jar"]