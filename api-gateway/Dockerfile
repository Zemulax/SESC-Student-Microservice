# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app

# Copy only the files needed for dependency resolution (faster rebuilds)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Build the application (cache dependencies)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built JAR
COPY --from=builder /app/target/*.jar app.jar

# Set environment variables (override in docker-compose if needed)
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080

# Optimized JVM for containers
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]