#stage 1 build

FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

#stage 2 runtime
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build ./target/jeanyvesart-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
