# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8064 available to the world outside this container
EXPOSE 8064

# The application's jar file
ARG JAR_FILE=target/role-service.jar

# Add the application's jar to the container
ADD ${JAR_FILE} role-service.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/role-service.jar"]