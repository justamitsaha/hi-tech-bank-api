#Build
mvn clean install

#RUN
For running locally connect to local Git repo  mentioned with file:///C:/
java -jar target/configServer-1.0-SNAPSHOT.jar
For connecting with remote repo need to pass param git.cred
java -jar target/configServer-1.0-SNAPSHOT.jar --git.cred=""

docker build -t justamitsaha/config-service .
docker tag onboardUserService  justamitsaha/config-service
sudo docker push justamitsaha/config-service

docker run -p 8080:8080 --restart always justamitsaha/config-service
If connecting to local repo no password is needed
docker run --name=config-service -p 9000:9000 justamitsaha/config-service
For connecting to Git repo password needs to be passed
docker run --name=config-service -p 9000:9000 --env git.cred=? justamitsaha/config-service
docker run --name=config-service -p 9000:9000 --net mynet --env git.cred=? justamitsaha/config-service