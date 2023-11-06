# Use the official Maven image as the parent image
FROM maven:3-openjdk-17-slim AS build

# Set the working directory to /app
WORKDIR /inventory-service

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the application source code to the container
COPY src/ ./src/

# Build the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image as the base image
FROM openjdk:17-slim AS runtime

# Set the working directory to /app
WORKDIR /inventory-service

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /inventory-service/target/*.jar /inventory-service/*.jar

# Copy the application source code from the build stage to the runtime stage
COPY --from=build /inventory-service/src/ ./src/

ARG AWS_ACCESS_KEY_ID

ARG AWS_SECRET_ACCESS_KEY

ARG AWS_REGION

ENV AWS_ACCESS_KEY_ID $AWS_ACCESS_KEY_ID

ENV AWS_SECRET_ACCESS_KEY $AWS_SECRET_ACCESS_KEY

ENV AWS_REGION $AWS_REGION


# Set the command to run the application when the container starts
CMD ["java", "-jar", "*.jar", "server"]