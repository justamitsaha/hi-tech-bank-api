#mvn clean install

#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com


# Add the application's jar to the container
COPY target/onboard-user-service-1.0-SNAPSHOT.jar onboard-user-service-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/onboard-user-service-1.0-SNAPSHOT.jar"]

#docker build -t justamitsaha/onboard-user-service .
#docker tag onboard-user-service  justamitsaha/onboard-user-service
#sudo docker push justamitsaha/onboard-user-service

#docker run -p 8080:8080 --restart always justamitsaha/onboard-user-service
#docker run justamitsaha/onboard-user-service
