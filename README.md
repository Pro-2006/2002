# Spring Boot JPA Application - com.klu

This is a Spring Boot Maven application with Spring Data JPA for database operations using MySQL.

## Project Structure

```
├── src/main/java/com/klu/
│   ├── Application.java              # Main Spring Boot application entry point
│   ├── entity/
│   │   └── User.java                 # JPA Entity class
│   ├── repository/
│   │   └── UserRepository.java       # JPA Repository with @Query examples
│   ├── service/
│   │   └── UserService.java          # Business logic service
│   └── controller/
│       └── UserController.java       # REST API endpoints
├── src/main/resources/
│   └── application.properties        # Database and Spring configuration
└── pom.xml                           # Maven configuration
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 5.7 or higher (or any compatible database)

## Setup Instructions

### 1. Create MySQL Database

```sql
CREATE DATABASE klucodedb;
USE klucodedb;
```

### 2. Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/klucodedb
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Install Dependencies

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Get All Users
```
GET /api/users
```

### Get User by ID
```
GET /api/users/{id}
```

### Get All Active Users
```
GET /api/users/active/list
```

### Search Users by Email
```
GET /api/users/search?email=example
```

### Create New User
```
POST /api/users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "isActive": true
}
```

### Update User
```
PUT /api/users/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "phone": "0987654321",
  "isActive": true
}
```

### Delete User
```
DELETE /api/users/{id}
```

## Key Features

### @Query Examples in UserRepository

1. **JPQL Query** - Find by name
   ```java
   @Query("SELECT u FROM User u WHERE u.name = :name")
   List<User> findByUserName(@Param("name") String name);
   ```

2. **Native SQL Query** - Search by email pattern
   ```java
   @Query(value = "SELECT * FROM users WHERE email LIKE %:email%", nativeQuery = true)
   List<User> searchByEmail(@Param("email") String email);
   ```

3. **Simple Queries** - Find all active users
   ```java
   @Query("SELECT u FROM User u WHERE u.isActive = true")
   List<User> findAllActiveUsers();
   ```

4. **Multiple Parameters** - Find by name and active status
   ```java
   @Query("SELECT u FROM User u WHERE u.name LIKE %:name% AND u.isActive = :active")
   List<User> findByNameAndActive(@Param("name") String name, @Param("active") Boolean active);
   ```

5. **Aggregation** - Count active users
   ```java
   @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
   long countActiveUsers();
   ```

## Database Operations

All CRUD operations are available through:
- **UserRepository** - Data access layer with @Query examples
- **UserService** - Business logic layer
- **UserController** - REST API endpoints

## Logging

Hibernate SQL logging is enabled by default. View SQL queries in the console:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
```

## Next Steps

1. Add more entities as needed
2. Extend UserRepository with more @Query methods
3. Add validation annotations (@NotNull, @Email, etc.)
4. Implement custom business logic in services
5. Add exception handling and error responses
6. Create unit and integration tests

## Notes

- Tables are auto-created (ddl-auto=update)
- Lombok is included to reduce boilerplate (getters/setters)
- MySQL 8.0 driver is included
- H2 database is available for testing