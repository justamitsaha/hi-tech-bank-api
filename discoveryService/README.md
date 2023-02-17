#Build
mvn clean install

#RUN
java -jar target/discoveryService-1.0-SNAPSHOT.jar
java -jar target/discoveryService-1.0-SNAPSHOT.jar --configHost="http://amit.com:9000/configService" --appProfile=""

#Docker Commands
docker build -t justamitsaha/discovery-service .
docker tag discovery-service  justamitsaha/discovery-service
sudo docker push justamitsaha/discovery-service
docker run -p 8081:8081 --restart always justamitsaha/discovery-service
docker run -p 8081:8081 justamitsaha/discovery-service

#Run Docker by passing config server URL as param
docker run -p 8081:8081 justamitsaha/discovery-service --configServer.URL=http://amit.config.com:9000/configService

docker run --env configHost=http://amit.com:9000/configService --env appProfile=localDocker -p 8081:8081 justamitsaha/discovery-service