# --- Stage 1: build your JAR ---
FROM maven:3-openjdk-17 AS builder
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source & build
COPY src ./src
RUN mvn package -DskipTests -B

# --- v Stage 2: run the JAR ---
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
