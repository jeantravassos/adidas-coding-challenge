# email-service
Adidas coding challenge - email-service microservice

##Prerequisite:
- java jdk 11
- maven 3.8.0

##1 Build
~~~~
mvn clean install
~~~~

##2 Run
####2.1 Run with Spring boot
~~~~
mvn spring-boot:run
~~~~
####2.2 Run with Docker
######2.2.1 Build the image
Build and register the image with Docker build:
~~~~
sudo docker build -t adidas/email-service .
~~~~
where:
- -t adidas/email-service: tag name for the image

######2.2.2 Run the image
Run the image with Docker run:
~~~~
sudo docker run -p8080:8080 --net=host adidas/email-service
~~~~
where:
- -p8080:8080: publish port for http
- --net=host: name of docker network
- adidas/email-service: image to run


Run the image with Docker run with a spring profile.
~~~~
sudo docker run -e "SPRING_PROFILES_ACTIVE=docker" -p8080:8080 --net=host adidas/email-service
~~~~
where
- -e "SPRING_PROFILES_ACTIVE=docker": is the same as `-Dspring.profiles.active=docker`, whereas `docker` is the name of spring profile to be used.

Spring will then read and load properties from `src/main/resources/application-docker.properties`.

##3 Swagger documentation
Swagger documentation is available on:
~~~~
/swagger-ui.html
~~~~
