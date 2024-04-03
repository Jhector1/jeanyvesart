#stage 1 build
#
FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
#RUN mvn clean package

#stage 2 runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build app/target/jeanyvesart-0.0.1-SNAPSHOT.jar ./app.jar
#EXPOSE 7899
##ENTRYPOINT ["java", "-jar", "./app.jar"]
#CMD ["java", "-jar", "./app.jar"]
#FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
#COPY ./target/jeanyvesart-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]