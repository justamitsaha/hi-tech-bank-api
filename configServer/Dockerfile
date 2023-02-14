#mvn clean install

#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com


# Add the application's jar to the container
COPY target/configServer-1.0-SNAPSHOT.jar configServer-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/configServer-1.0-SNAPSHOT.jar"]

#docker build -t justamitsaha/config-service .
#docker tag onboardUserService  justamitsaha/config-service
#sudo docker push justamitsaha/config-service

#docker run -p 8080:8080 --restart always justamitsaha/config-service
#docker run justamitsaha/config-service