# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies for caching
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Standard ports
EXPOSE 8080

# Environment variables with sensible defaults for non-prod
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "app.jar"]
