# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
MAINTAINER Avishek Das <avishek.akd@gmail.com>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8052 available to the world outside this container
EXPOSE 8052

# The application's jar file
ARG JAR_FILE=target/zuul-gateway.jar

# Add the application's jar to the container
ADD ${JAR_FILE} zuul-gateway.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/zuul-gateway.jar"]