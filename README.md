# In-Memory Cache with Spring Boot and Caffeine

## Overview

This project demonstrates how to build an in-memory cache for a Spring Boot application using Spring Cache and Caffeine. The application provides a simple RESTful API for managing users and caches user data in memory to reduce latency and improve performance.

## Features

- **In-Memory Caching**: Uses Caffeine for in-memory caching to achieve low latency and high throughput.
- **CRUD Operations**: Provides endpoints for creating, reading, updating, and deleting users.
- **Spring Boot Integration**: Built using Spring Boot for rapid development and deployment.
- **H2 Database**: Uses an in-memory H2 database for simplicity and ease of testing.

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6.3** or higher

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/in-memory-cache-springboot.git
    cd in-memory-cache-springboot
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

#### Sample Requests
##### Get all users:

```bash
curl -X GET http://localhost:8080/api/users
```

##### Get a user by ID:
```bash
curl -X GET http://localhost:8080/api/users/1
```

##### Create a new user:
```bash
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}'
```

##### Update an existing user:
```bash
curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" -d '{"name": "Jane Doe", "email": "jane.doe@example.com"}'
```

##### Delete a user:
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

#### Check cache
```bash
curl -X GET http://localhost:8080/api/users/cache/stats
```
#### output
```json
{"usersCache":"CacheStats{hitCount=8, missCount=0, loadSuccessCount=0, loadFailureCount=0, totalLoadTime=0, evictionCount=0, evictionWeight=0}"}
```

H2 Console
You can access the H2 database console at
```bash 
http://localhost:8080/h2-console
```
The default JDBC URL is `jdbc:h2:mem:testdb`

