#Build
mvn clean install

#RUN
java -jar target/onboard-user-service-1.0-SNAPSHOT.jar

#Docker Commands
docker build -t justamitsaha/onboard-user-service .

#While running locally need to pass profile as appProfile and config server URL. If the config server is running on local host then
docker run --name=onboard-user-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/onboard-user-service


#While running locally need to pass profile as appProfile and config server URL. If the config server is running as docker then
docker run --name=onboard-user-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/onboard-user-service