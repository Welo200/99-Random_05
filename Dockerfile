# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Copy the data JSON files to a specific directory in the container
COPY src/main/java/com/example/MiniProject1/data /app/data

# Install Maven and build the project
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

# Set environment variables for the JSON file paths
ENV USERS_JSON_PATH=/app/data/users.json
ENV PRODUCTS_JSON_PATH=/app/data/products.json
ENV CARTS_JSON_PATH=/app/data/carts.json
ENV ORDERS_JSON_PATH=/app/data/orders.json

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/MiniProject1-1.0.0.jar"]