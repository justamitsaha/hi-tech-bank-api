
#Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

#Information around who maintains the image
MAINTAINER justamitsaha@gmail.com

EXPOSE 8080

# Add the application's jar to the container
COPY target/redis-cache-service-1.0-SNAPSHOT.jar redis-cache-service-1.0-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/redis-cache-service-1.0-SNAPSHOT.jar"]

# CMD ["java","-jar","/discovery-service-1.0-SNAPSHOT.jar"]

