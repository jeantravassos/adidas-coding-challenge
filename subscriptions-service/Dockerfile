FROM openjdk:11-jre-slim

EXPOSE 8080

ADD target/subscriptions-service*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
