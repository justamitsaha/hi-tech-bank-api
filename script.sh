#!/bin/bash

# Change to your project directory
cd config-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image

cd discovery-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image

cd api-gate-way-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image

cd redis-cache-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image

cd onboard-user-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image

cd authentication-jwt-service/
# Run mvn clean install and build image
mvn clean install
mvn spring-boot:build-image


