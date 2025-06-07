# Use official Java 17 base image
FROM eclipse-temurin:17-jdk

# Create app directory
WORKDIR /app

# Copy all source code
COPY . .

# Build the JAR file using Maven
RUN apt-get update && apt-get install -y maven && mvn clean package

# Use the built jar file
CMD ["java", "-jar", "target/tracking-number-generator-1.0.0.jar"]
