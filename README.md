# KanbanFlow API

A robust backend service for a collaborative project management tool built with Spring Boot. This API provides a complete solution for managing projects, tasks, and users with modern features like JWT authentication, real-time notifications, caching, and asynchronous processing.

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [API Endpoints](#-api-endpoints)
- [Architecture](#-architecture)
- [Monitoring & Logging](#-monitoring--logging)
- [Security](#-security)
- [Development](#-development)

## 🎯 Overview

KanbanFlow API is a RESTful backend service designed for project management applications following the Kanban methodology. It enables teams to organize work, track progress, and collaborate effectively through a structured API interface.

## ✨ Features

- **User Management**: User registration, authentication, and profile management
- **Project Management**: Create and manage projects with role-based access control
- **Task Management**: Full CRUD operations for tasks within projects
- **JWT Authentication**: Secure token-based authentication
- **Email Notifications**: Automated email notifications for important events
- **Caching**: Redis-based caching for improved performance
- **Event-Driven Architecture**: Kafka integration for asynchronous event processing
- **File Upload Support**: Handle file attachments (up to 2MB per file)
- **Health Monitoring**: Spring Actuator endpoints for application monitoring
- **Structured Logging**: JSON-formatted logs with Logstash encoder
- **Role-Based Access Control**: Admin and user roles with different permissions
- **Async Processing**: Non-blocking operations for better scalability
- **Scheduled Tasks**: Background job processing

## 🛠 Technology Stack

### Core Framework
- **Spring Boot 3.5.5** - Main application framework
- **Java 17** - Programming language
- **Maven** - Dependency management and build tool

### Database & Persistence
- **MySQL** - Relational database
- **Spring Data JPA** - Data access layer
- **Hibernate** - ORM framework

### Security
- **Spring Security** - Authentication and authorization
- **JJWT 0.12.3** - JWT token creation and validation

### Caching & Messaging
- **Redis** - Distributed caching
- **Apache Kafka** - Event streaming platform
- **Apache Zookeeper** - Kafka coordination

### Additional Libraries
- **Lombok** - Reduce boilerplate code
- **Spring Validation** - Request validation
- **Spring Mail** - Email functionality
- **Spring Actuator** - Application monitoring
- **Logstash Logback Encoder 7.4** - Structured logging

### Development Tools
- **Spring DevTools** - Hot reload during development
- **Docker Compose** - Container orchestration

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Redis** (optional, for caching)
- **Docker & Docker Compose** (for Kafka/Zookeeper)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/RakeshJakka5158/kanban-api.git
cd kanban-api
```

### 2. Set Up MySQL Database

Create a MySQL database:

```sql
CREATE DATABASE kanbanflow_db;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kanbanflow_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# Email Configuration
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD
```

### 4. Start Kafka & Zookeeper (using Docker Compose)

```bash
docker-compose up -d
```

This will start:
- Zookeeper on port `2181`
- Kafka on port `9092`

### 5. Start Redis (optional)

```bash
redis-server
```

Or use Docker:

```bash
docker run -d -p 6379:6379 redis
```

### 6. Build the Application

```bash
./mvnw clean install
```

Or on Windows:

```bash
mvnw.cmd clean install
```

### 7. Run the Application

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## ⚙️ Configuration

### Application Profiles

The application uses Spring profiles. Default profile is `dev`:

```properties
spring.profiles.active=dev
```

### Port Configuration

- **API Server**: `8080` (default)
- **Actuator**: `9090` (management endpoints)

### File Upload Limits

- Max file size: `2MB`
- Max request size: `2MB`

### Database Configuration

The application uses `spring.jpa.hibernate.ddl-auto=update` which automatically creates/updates tables based on entity definitions.

## 🔌 API Endpoints

### Authentication (`/api/auth`)
- `POST /api/auth/login` - User login
- `POST /api/auth/**` - Other auth operations (public)

### Users (`/api/users`)
- `POST /api/users/register` - Register new user (public)
- `GET /api/users/**` - User operations (authenticated)

### Projects (`/api/projects`)
- `POST /api/projects/create` - Create project (ADMIN only)
- `POST /api/projects/*/members` - Add project members (ADMIN only)
- Other endpoints (authenticated)

### Tasks (`/api/tasks`)
- Full CRUD operations for tasks (authenticated)

### Actuator (`/actuator`)
- `GET /actuator/health` - Application health status
- `GET /actuator/info` - Application information
- `GET /actuator/metrics` - Application metrics
- `GET /actuator/app-stats` - Custom application statistics
- `GET /actuator/beans` - Registered beans
- `GET /actuator/mappings` - Request mappings

## 🏗 Architecture

### Project Structure

```
src/main/java/com/kanbanflow/kanban_api/
├── actuator/endpoint/          # Custom actuator endpoints
├── config/                     # Configuration classes
│   ├── filter/                 # Security and MDC filters
│   ├── AppConfig.java
│   ├── AsyncConfig.java
│   ├── CacheConfig.java
│   └── SecurityConfig.java
├── controller/                 # REST Controllers
│   ├── AuthController.java
│   ├── ProjectController.java
│   ├── TaskController.java
│   └── UserController.java
├── dto/                        # Data Transfer Objects
├── entity/                     # JPA Entities
│   ├── User.java
│   ├── Project.java
│   └── Task.java
├── exception/                  # Custom exceptions
├── repository/                 # JPA Repositories
├── service/                    # Business logic
│   ├── impl/                   # Service implementations
│   ├── Kafka/                  # Kafka services
│   ├── FileStorageService.java
│   ├── NotificationService.java
│   ├── ProjectService.java
│   ├── ScheduledTaskService.java
│   ├── TaskService.java
│   └── UserService.java
└── util/                       # Utility classes
```

### Key Components

1. **Controllers**: Handle HTTP requests and responses
2. **Services**: Contain business logic
3. **Repositories**: Data access layer
4. **Entities**: Database models (User, Project, Task)
5. **DTOs**: Data transfer objects for API communication
6. **Filters**: JWT authentication and MDC logging filters
7. **Config**: Application configuration (Security, Caching, Async, etc.)

### Design Patterns

- **Dependency Injection**: Spring's IoC container
- **Repository Pattern**: Data access abstraction
- **DTO Pattern**: Separate internal models from API contracts
- **Filter Chain**: Request processing pipeline
- **Event-Driven**: Kafka for asynchronous processing

## 📊 Monitoring & Logging

### Spring Actuator

Access monitoring endpoints at `http://localhost:9090/actuator`

**Available Endpoints:**
- `/health` - Application health check
- `/info` - Application metadata
- `/metrics` - Performance metrics
- `/app-stats` - Custom application statistics
- `/beans` - Spring beans information
- `/mappings` - Request mapping details

### Logging

- **Format**: JSON (using Logstash encoder)
- **Location**: `logs/application.json`
- **Features**: 
  - Structured logging
  - MDC (Mapped Diagnostic Context) for request tracing
  - Correlation IDs for tracking requests

## 🔐 Security

### Authentication

- **JWT-based authentication** with Bearer tokens
- Tokens must be included in the `Authorization` header

```
Authorization: Bearer <your-jwt-token>
```

### Authorization

- **Role-Based Access Control (RBAC)**
  - `ROLE_ADMIN`: Full access including project creation
  - `ROLE_USER`: Standard user access

### Security Features

- Stateless session management
- CSRF protection disabled (for REST API)
- Password encryption
- Secure JWT token generation
- Request filtering and validation

## 💻 Development

### Running Tests

```bash
./mvnw test
```

### Building for Production

```bash
./mvnw clean package -DskipTests
```

The JAR file will be created in the `target/` directory.

### Hot Reload

The application includes Spring DevTools for automatic restart during development.

### Code Style

- Uses Lombok annotations to reduce boilerplate
- Follows Spring Boot best practices
- RESTful API design principles

## 📝 Additional Notes

### Email Configuration

Configure Gmail SMTP settings or your preferred email provider in `application.properties`. For Gmail, you'll need to generate an **App Password** if 2FA is enabled.

### Kafka Topics

The application uses Kafka for event-driven communication. Topics are automatically created when events are published.

### Redis Cache

Caching is enabled at the application level. Configure cache names in `CacheConfig.java`.

### File Storage

Uploaded files are stored in the `uploads/` directory. Ensure the application has write permissions.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👥 Contact

**Project Owner**: RakeshJakka5158

**Repository**: [https://github.com/RakeshJakka5158/kanban-api](https://github.com/RakeshJakka5158/kanban-api)

---

*Built with ❤️ using Spring Boot*
