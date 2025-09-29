# Use Java 22 JDK
FROM openjdk:23-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/AiCareerCoach.jar AiCareerCoach.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "AiCareerCoach.jar"]
