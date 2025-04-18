FROM maven:3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN ["mvn", "clean", "package", "-DskipTests"]

FROM openjdk:17-jdk-slim
WORKDIR /app
# Add OpenTelemetry Java agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.27.0/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
COPY --from=build /app/target/library-api-1.0.jar /app/library-api-1.0.jar
EXPOSE 8080

# Set the Java agent for OpenTelemetry
ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/library-api-1.0.jar"]
