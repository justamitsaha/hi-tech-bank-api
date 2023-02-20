#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com


# Add the application's jar to the container
COPY target/configServer-1.0-SNAPSHOT.jar configServer-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/configServer-1.0-SNAPSHOT.jar"]

