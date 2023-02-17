#Build
mvn clean install

#RUN
java -jar target/apiGateWay-1.0-SNAPSHOT.jar
java -jar target/apiGateWay-1.0-SNAPSHOT.jar --configServer.URL="http://localhost:9000/configService"

#Docker Commands
#docker build -t justamitsaha/api-gate-way-service .
#docker tag onboardUserService  justamitsaha/api-gate-way-service
#sudo docker push justamitsaha/api-gate-way-service
#docker run -p 8080:8080 --restart always justamitsaha/api-gate-way-service
#docker run -p 8080:8080 justamitsaha/api-gate-way-service

#Run Docker by passing config server URL as param
docker run -p 8080:8080 justamitsaha/api-gate-way-service --configServer.URL=http://amit.config.com:9000/configService

docker run --env configServer.URL=http://amit.config.com:9000/configService -p 8080:8080 justamitsaha/api-gate-way-service