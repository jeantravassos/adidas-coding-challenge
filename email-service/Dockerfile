FROM openjdk:11-jre-slim

EXPOSE 8082

ADD target/email-service*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
