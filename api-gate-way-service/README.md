#Build
mvn clean install

#RUN
java -jar target/api-gate-way-service-1.0-SNAPSHOT.jar

#Docker Commands
docker build -t justamitsaha/api-gate-way-service .
#docker tag onboard-user-service  justamitsaha/api-gate-way-service
#sudo docker push justamitsaha/api-gate-way-service
#docker run -p 8080:8080 --restart always justamitsaha/api-gate-way-service
#docker run -p 8080:8080 justamitsaha/api-gate-way-service

#Run Docker by passing config server URL as param
#While running locally need to pass profile as appProfile and config server URL. If the config server is running on local host then
docker run -p 8080:8080 --name=api-gate-way-service-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/api-gate-way-service


#While running locally need to pass profile as appProfile and config server URL. If the config server is running as docker then
docker run -p 8080:8080 --name=api-gate-way-service-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/api-gate-way-service