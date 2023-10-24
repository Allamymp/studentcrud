FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install openjdk-19-jdk -y
COPY . .
RUN apt-get install maven -y
RUN mvn clean install
FROM openjdk:19-jdk-slim
EXPOSE 8080
COPY --from=build /target/api-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]