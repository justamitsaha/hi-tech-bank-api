@echo off

cd config-service/
set /p user_action="Do you want to run config service?enter y/n"
if "%user_action%"=="y" (
    mvn clean install
    cd target/
    start /B java -jar config-service-1.0-SNAPSHOT.jar > output.log 2>&1
    cd ../
) else (
    echo "Skipping config server"
)

cd ../discovery-service/
set /p user_action="Do you want to run discovery service?enter y/n"
if "%user_action%"=="y" (
    mvn clean install
    cd target/
    start /B java -jar discovery-service-1.0-SNAPSHOT.jar > output.log 2>&1
    cd ../
) else (
    echo "Skipping config server"
)

cd ../api-gate-way-service/
set /p user_action="Do you want to run api gateway service?enter y/n"
if "%user_action%"=="y" (
    mvn clean install
    cd target/
    start /B java -jar api-gate-way-service-1.0-SNAPSHOT.jar > output.log 2>&1
) else (
    echo "Skipping config server"
)

cd ../redis-cache-service/
set /p user_action="Do you want to run redis-cache-service?enter y/n"
if "%user_action%"=="y" (
    mvn clean install
    cd target/
    start /B java -jar redis-cache-service-1.0-SNAPSHOT.jar > output.log 2>&1
    cd ../
) else (
    echo "Skipping config server"
)

cd ../onboard-user-service/
set /p user_action="Do you want to run onboard-user-service?enter y/n"
if "%user_action%"=="y" (
    mvn clean install
    cd target/
    start /B java -jar onboard-user-service-1.0-SNAPSHOT.jar > output.log 2>&1
    cd ../
) else (
    echo "Skipping config server"
)