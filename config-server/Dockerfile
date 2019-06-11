# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8051 available to the world outside this container
EXPOSE 8051

# The application's jar file
ARG JAR_FILE=target/config-server.jar

# Add the application's jar to the container
ADD ${JAR_FILE} config-server.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/config-server.jar"]