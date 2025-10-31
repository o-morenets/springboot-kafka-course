# Spring Boot Kafka Tutorial

A simple Spring Boot application demonstrating Kafka producer and consumer with both String and JSON message support.

## Prerequisites

- Java 17
- Docker and Docker Compose
- Maven

## Quick Start

### 1. Start Kafka

```bash
docker-compose up -d
```

This will start:
- **Kafka** on `localhost:9092`
- **Kafka UI** on `http://localhost:8081`

### 2. Run the Application

```bash
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Send Messages

**String Message:**
```bash
curl 'http://localhost:8080/api/v1/kafka/publish?message=Hello%20World!'
```

**JSON Message:**
```bash
curl -X POST http://localhost:8080/api/v1/kafka/publish \
  -H "Content-Type: application/json" \
  -d '{"id":1,"firstName":"John","lastName":"Doe","createdAt":"2025-10-29T12:30:45.999Z"}'
```

### 4. Monitor Messages

Check the application logs to see consumed messages, or visit the Kafka UI at `http://localhost:8092`

## Stop Services

```bash
docker-compose down
```

## Blog posts on JavaGuides
Spring Boot Kafka Producer Consumer Example Tutorial - https://www.javaguides.net/2022/05/spring-boot-kafka-producer-consumer-example-tutorial.html

Spring Boot Kafka JsonSerializer and JsonDeserializer Example - https://www.javaguides.net/2022/05/spring-boot-kafka-jsonserializer-and-Jsondeserializer-example.html
