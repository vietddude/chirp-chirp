FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y postgresql-client curl

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 5005

ENTRYPOINT ["java", "-jar", "app.jar"]