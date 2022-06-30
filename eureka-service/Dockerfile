FROM openjdk:11-jre-slim
LABEL Description="Eureka Server"

EXPOSE 8761 8787

ADD target/eureka-service*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
