#Build
mvn clean install

#RUN
java -jar target/configServer-1.0-SNAPSHOT.jar
java -jar target/configServer-1.0-SNAPSHOT.jar --git.cred=""

docker build -t justamitsaha/config-service .
docker tag onboardUserService  justamitsaha/config-service
sudo docker push justamitsaha/config-service

docker run -p 8080:8080 --restart always justamitsaha/config-service
docker run --name=configServer -p 9000:9000 --env git.cred= justamitsaha/config-service