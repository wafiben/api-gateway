# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copy source
COPY src ./src

# Build jar
RUN mvn -B clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 9000 (matches your Render setting)
EXPOSE 9000

# Use PORT from environment variable (falls back to 9000)
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-9000} -jar app.jar"]