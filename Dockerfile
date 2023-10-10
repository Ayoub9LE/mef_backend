# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.3-openjdk-17 as builder

# Copy local code to the container image.
WORKDIR /app
COPY . .

# Build a release artifact.
RUN mvn clean package -DskipTests

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK because it doesn't have a license restriction for commercial use.
FROM openjdk:17-oracle
# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/*.jar /mef_backend.jar

# Run the web service on container startup.
CMD ["java", "-jar", "/mef_backend.jar"]
