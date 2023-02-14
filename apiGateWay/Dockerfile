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

#docker build -t justamitsaha/api-gate-way-service .
#docker tag onboardUserService  justamitsaha/api-gate-way-service
#sudo docker push justamitsaha/api-gate-way-service

#docker run -p 8080:8080 --restart always justamitsaha/api-gate-way-service
#docker run -p 8080:8080 justamitsaha/api-gate-way-service