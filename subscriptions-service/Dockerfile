FROM openjdk:11-jre-slim

EXPOSE 8083

ADD target/subscriptions-service*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
