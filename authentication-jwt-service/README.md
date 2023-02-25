#Build
mvn clean install

#RUN
java -jar target/authentication-jwt-service-1.0-SNAPSHOT.jar

#Docker Commands
docker build -t justamitsaha/authentication-jwt-service .

#While running locally need to pass profile as appProfile and config server URL. If the config server is running on local host then
docker run --name=authentication-jwt-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/authentication-jwt-service


#While running locally need to pass profile as appProfile and config server URL. If the config server is running as docker then
docker run --name=authentication-jwt-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/authentication-jwt-service