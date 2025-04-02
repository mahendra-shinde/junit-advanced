FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN ["mvn", "clean", "package", "-DskipTests"]

FROM openjdk:11-jdk 
COPY --from=build /app/target/library-api-1.0.jar /app/library-api-1.0.jar
COPY applicationinsights-agent-3.4.8.jar /applicationinsights-agent.jar
EXPOSE 8080

ENTRYPOINT ["java", "-javaagent:/applicationinsights-agent.jar", "-jar", "/app/library-api-1.0.jar"]