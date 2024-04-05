# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/DevOps_Project-1.0.jar /app

# Specify the command to run your application when the container starts
CMD ["java", "-jar", "DevOps_Project-1.0.jar"]