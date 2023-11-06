# Use the official Maven image as the parent image
FROM maven:3-openjdk-17-slim AS build

# Set the working directory to /app
WORKDIR /author-service

# Copy the pom.xml file to the container
COPY pom.xml .

# # Download the project dependencies
# RUN mvn dependency:go-offline

# Copy the application source code to the container
COPY src/ ./src/

# Build the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-slim AS runtime

# Set the working directory to /app
WORKDIR /author-service

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /author-service/target/*.jar /author-service/*.jar

# Copy the application source code from the build stage to the runtime stage
COPY --from=build /author-service/src/ ./src/


# Set the command to run the application when the container starts
CMD ["java", "-jar", "*.jar", "server"]