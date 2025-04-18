# Use Amazon Corretto 17 Alpine as the base image
FROM amazoncorretto:17.0.8-alpine3.18

# Set working directory inside the container
WORKDIR /app

# Copy Spring Boot JAR file into the container
COPY target/better-wellness-user-api-1.0.0.jar app.jar

# Copy application.properties into the same directory
COPY src/main/resources/application.properties .

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application with config location set to current directory
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=./application.properties"]
