#Build
mvn clean install

#RUN
java -jar target/redis-cache-service-1.0-SNAPSHOT.jar

#Docker Commands
docker build -t justamitsaha/redis-cache-service .

#While running locally need to pass profile as appProfile and config server URL. If the config server is running on local host then
docker run --name=redis-cache-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/redis-cache-service


#While running locally need to pass profile as appProfile and config server URL. If the config server is running as docker then
docker run --name=redis-cache-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/redis-cache-service