# Use Maven to build the project
FROM maven:3.9.4-eclipse-temurin-17 as builder
WORKDIR /app

# Copy the entire project and build it
COPY . .
RUN mvn clean package -DskipTests

# List the target directory to see what JARs were created
RUN ls -la /app/target/

# Use a lightweight Java runtime to run the built JAR
FROM eclipse-temurin:17
WORKDIR /app

# Copy only the shaded JAR from the builder stage (not the original)
COPY --from=builder /app/target/ai-jewelry-backend-1.0-SNAPSHOT.jar app.jar

# Run your app using -jar (not -cp)
CMD ["java", "-jar", "app.jar"]