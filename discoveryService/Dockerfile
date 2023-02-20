
#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com

EXPOSE 8081

# Add the application's jar to the container
COPY target/discoveryService-1.0-SNAPSHOT.jar discoveryService-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/discoveryService-1.0-SNAPSHOT.jar"]

# CMD ["java","-jar","/discoveryService-1.0-SNAPSHOT.jar"]

