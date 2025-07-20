# Stage 1: Build with Maven (optional if JAR already exists)
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create final image
FROM openjdk:21-jdk-slim

LABEL maintainer="Naresh Kushwaha"

WORKDIR /app

# Copy JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar


# Expose port (override in docker-compose)
EXPOSE 8082

# Entry point (can be overridden by docker-compose)
ENTRYPOINT [  "java", "-jar", "app.jar"]
