#mvn clean install

#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com

EXPOSE 8080

# Add the application's jar to the container
COPY target/apiGateWay-1.0-SNAPSHOT.jar apiGateWay-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/apiGateWay-1.0-SNAPSHOT.jar"]

