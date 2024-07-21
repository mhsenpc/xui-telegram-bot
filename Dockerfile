# Use a base image that contains JDK
FROM eclipse-temurin:21

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/bot*.jar app.jar

# Copy the keystore file to the container
COPY src/main/resources/keystore.p12 keystore.p12

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Set the entry point for the container to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]