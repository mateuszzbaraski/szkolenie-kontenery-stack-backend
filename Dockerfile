FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the Gradle files first for better layer caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Build the application
RUN ./gradlew build -x test

RUN ls -la build/libs

# Move the JAR file to the root of the container
RUN mv build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Clean up build files to reduce image size
RUN rm -rf build gradle gradlew build.gradle settings.gradle src

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
