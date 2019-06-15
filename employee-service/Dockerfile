# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8060 available to the world outside this container
EXPOSE 8060

# The application's jar file
ARG JAR_FILE=target/employee-service.jar

# Add the application's jar to the container
ADD ${JAR_FILE} employee-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/employee-service.jar"]