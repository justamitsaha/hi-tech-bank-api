#mvn clean install

#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com


# Add the application's jar to the container
COPY target/onboardUserService-1.0-SNAPSHOT.jar onboardUserService-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/onboardUserService-1.0-SNAPSHOT.jar"]

#docker build -t justamitsaha/onboard-user-service .
#docker tag onboardUserService  justamitsaha/onboard-user-service
#sudo docker push justamitsaha/onboard-user-service

#docker run -p 8080:8080 --restart always justamitsaha/onboard-user-service
#docker run justamitsaha/onboard-user-service
