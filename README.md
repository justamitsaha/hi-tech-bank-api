mvn clean install

mvn spring-boot:build-image

or

docker build -t justamitsaha/discovery-service .

1>Config Service
docker run --name=config-service -p 9000:9000 --env git.cred=? justamitsaha/config-service
docker run --name=config-service -p 9000:9000 --net mynet --env git.cred=? justamitsaha/config-service

2> Discovery Service
docker run -p 8081:8081 --name=discovery-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService justamitsaha/discovery-service
docker run -p 8081:8081 --name=discovery-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService justamitsaha/discovery-service

3> API Gate way
docker run -p 8080:8080 --name=api-gate-way-service-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/api-gate-way-service

docker run -p 8080:8080 --name=api-gate-way-service-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/api-gate-way-service

4> Redis Cache
docker run --name=redis-cache-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/redis-cache-service

docker run --name=redis-cache-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/redis-cache-service

5>On boarding
docker run --name=onboard-user-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/onboard-user-service

docker run --name=onboard-user-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/onboard-user-service

6>Authentication
docker run --name=authentication-jwt-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://host.docker.internal:9000/configService  justamitsaha/authentication-jwt-service

docker run --name=authentication-jwt-service --net mynet --env spring.profiles.active=localDocker --env spring.config.import=configserver:http://config-service:9000/configService  justamitsaha/authentication-jwt-service

Actuator URL
http://localhost:8080/actuator/info
http://localhost:8080/actuator/

http://localhost:8080/onBoardingService/actuator/
http://localhost:8080/onBoardingService/actuator/info

POST
http://localhost:8080/redisCache/actuator/refresh
http://localhost:8080/onBoardingService/actuator/shutdown/
