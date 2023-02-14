#mvn clean install

#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com

EXPOSE 8081

# Add the application's jar to the container
COPY target/discoveryService-1.0-SNAPSHOT.jar discoveryService-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/discoveryService-1.0-SNAPSHOT.jar"]

#docker build -t justamitsaha/discovery-service .
#docker tag discovery-service  justamitsaha/discovery-service
#sudo docker push justamitsaha/discovery-service

#docker run -p 8081:8081 --restart always justamitsaha/discovery-service
#docker run -p 8081:8081 justamitsaha/discovery-service