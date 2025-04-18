# Library API Documentation

## Overview
The Library API is a RESTful service designed to manage a collection of books. It provides endpoints to create, read, update, and delete book records. The project is containerized using Docker and includes Jaeger for distributed tracing.

## Features
- CRUD operations for Books, Members and IssueBooks.
- Integrated distributed tracing with Jaeger.
- Docker Compose for easy setup and deployment.

## Prerequisites
- Docker and Docker Compose installed on your system.

## Project Structure
```
/library-api
├── src/               # Source code for the API
├── docker-compose.yml # Docker Compose configuration
├── Dockerfile         # Dockerfile for the API
├── Readme.md          # Project documentation
```

## Steps to Launch the Application and Jaeger

1. **Clone the Repository**  

    ```bash
    git clone https://github.com/mahendra-shinde/library-api-jaeger
    cd library-api-jaeger
    ```

2. **Set Up OpenTelemetry Java Agent**  
    Ensure the OpenTelemetry Java agent is downloaded and configured in the `Dockerfile`. The agent is automatically added during the build process.

3. **Build and Start Services**  
    Use Docker Compose to build and start the application and Jaeger.  

    ```bash
    docker-compose up --build
    ```

4. **Access the Application**  

    - The Library API will be available at: `http://localhost:8080`
    - Jaeger UI will be available at: `http://localhost:16686`

5. **Stop Services**  

    To stop the services, run:  

    ```bash
    docker-compose down
    ```

## API Endpoints from `library-api`
| Method | Endpoint              | Description                     |
|--------|-----------------------|---------------------------------|
| GET    | /api/books            | Get all books                  |
| POST   | /api/books            | Add a new book                 |
| GET    | /api/books/{id}       | Get a book by ID               |
| PUT    | /api/books/{id}       | Update a book by ID            |
| DELETE | /api/books/{id}       | Delete a book by ID            |
| GET    | /api/members          | Get all members                |
| POST   | /api/members          | Add a new member               |
| GET    | /api/members/{id}     | Get a member by ID             |
| PUT    | /api/members/{id}     | Update a member by ID          |
| DELETE | /api/members/{id}     | Delete a member by ID          |
| GET    | /api/issues           | Get all book issues            |
| POST   | /api/issues           | Add a new book issue           |
| GET    | /api/issues/{id}      | Get a book issue by ID         |
| PUT    | /api/issues/{id}      | Update a book issue by ID      |
| DELETE | /api/issues/{id}      | Delete a book issue by ID      |


## Distributed Tracing with Jaeger
Jaeger is integrated into the application to trace requests and monitor performance. You can view traces in the Jaeger UI at `http://localhost:16686`.

## OpenTelemetry Configuration

1. **Dockerfile**  
   The Dockerfile is used to build the application container image. During the build process, the OpenTelemetry Java agent is downloaded and added to the application. This agent is responsible for automatically instrumenting the application to capture telemetry data such as traces and metrics. 

   The agent is configured to export traces to Jaeger, a popular open-source distributed tracing system. This is achieved by setting the necessary environment variables and ensuring the agent is included in the Java application's startup command. Typically, the agent is added as a JVM argument, such as:

   ```dockerfile
   CMD ["java", "-javaagent:/path/to/opentelemetry-javaagent.jar", "-jar", "app.jar"]
   ```

2. **Docker Compose**  

    ```yaml
    services:
        library-api:
            build:
                context: .
                dockerfile: Dockerfile
            ports:
                - "8080:8080"
            environment:
                - OTEL_EXPORTER_OTLP_ENDPOINT=http://jaeger:4317
                - OTEL_SERVICE_NAME=library-api
                - OTEL_EXPORTER_OTLP_PROTOCOL=grpc
        jaeger:
            image: jaegertracing/all-in-one:1.41
            environment:
                - COLLECTOR_OTLP_ENABLED=true
            ports:
                - "16686:16686" # Jaeger UI
                - "4317:4317" # gRPC endpoint for OTLP
    ```

    The `docker-compose.yml` file defines the services required to run the application and Jaeger. It includes the following configurations:

   - **Library API Service**  
     The `library-api` service is configured to use the OpenTelemetry agent. The following environment variables are set:
     - `OTEL_EXPORTER_OTLP_ENDPOINT`: Specifies the Jaeger endpoint for exporting telemetry data (e.g., `http://jaeger:4317`).
     - `OTEL_SERVICE_NAME`: Defines the service name for tracing (e.g., `library-api`).
     - `OTEL_EXPORTER_OTLP_PROTOCOL`: Specifies the protocol used for exporting telemetry data (e.g., `grpc`).

   - **Jaeger Service**  
     The `jaeger` service is configured to enable the OTLP collector and expose the necessary ports:
     - `16686`: For accessing the Jaeger UI.
     - `4317`: For receiving telemetry data via gRPC.

## License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.