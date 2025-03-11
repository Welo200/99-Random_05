# Use the same base image as the lab Dockerfile
FROM openjdk:25-ea-4-jdk-oraclelinux9

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the target directory
COPY ./target/mini1.jar ./target/mini1.jar

# Copy the data JSON files to a specific directory in the container
COPY src/main/java/com/example/data /app/data

# Set environment variables for the JSON file paths
ENV USERS_JSON_PATH=/app/data/users.json
ENV PRODUCTS_JSON_PATH=/app/data/products.json
ENV CARTS_JSON_PATH=/app/data/carts.json
ENV ORDERS_JSON_PATH=/app/data/orders.json

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "target/mini1.jar"]
#
## Build the Docker image
#docker build -t mini1-app .
#
## Run the Docker container
#docker run -p 8080:8080 mini1-app
#
## Run in detached mode
#docker run -d -p 8080:8080 mini1-app
#
## Check running containers
#docker ps
#
## Stop the container
#docker stop <container-id>
#
## Remove the container
#docker rm <container-id>
#
## Remove the Docker image
#docker rmi mini1-app
