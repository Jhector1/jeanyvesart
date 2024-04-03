#stage 1 build

FROM maven:3.8.4-openjdk-17 AS build
ARG JAR_FILE=target/*.jar
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package

#stage 2 runtime
#FROM openjdk:17-jdk
#ARG JAR_FILE=target/*.jar
#WORKDIR /app
COPY --from=build app/target/jeanyvesart-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 7899
#ENTRYPOINT ["java", "-jar", "./app.jar"]
#CMD ["java", "-jar", "./app.jar"]
#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/jeanyvesart-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar","/app.jar"]