FROM openjdk:11-jre-slim

EXPOSE 8081

ADD target/public-service*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
