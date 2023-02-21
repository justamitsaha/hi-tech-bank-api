#Build
mvn clean install

#RUN
java -jar target/discoveryService-1.0-SNAPSHOT.jar

#Docker Commands
docker build -t justamitsaha/discovery-service .
docker tag discovery-service  justamitsaha/discovery-service
sudo docker push justamitsaha/discovery-service
docker run -p 8081:8081 --restart always justamitsaha/discovery-service
docker run -p 8081:8081 justamitsaha/discovery-service

#While running locally need to pass profile as appProfile and config server URL. If the config server is running on local host then
docker run --env configHost=http://host.docker.internal:9000/configService --env appProfile=localDocker -p 8081:8081 --name=discovery-server justamitsaha/discovery-service


#While running locally need to pass profile as appProfile and config server URL. If the config server is running as docker then
docker run --env configHost=http://config-service:9000/configService --env appProfile=localDocker -p 8081:8081 --net mynet --name=discovery-server justamitsaha/discovery-service