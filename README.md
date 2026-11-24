# 🚀 WorkForceX – Employee Management System

<p align="center">
  <strong>A Secure & Scalable Employee Management REST API</strong>
  <br>
  Built with Java 21, Spring Boot, Spring Security, JWT and MySQL.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring_Boot-Backend-brightgreen?logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring_Security-JWT-green?logo=springsecurity" alt="Spring Security">
  <img src="https://img.shields.io/badge/MySQL-Database-blue?logo=mysql" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-Build-red?logo=apachemaven" alt="Maven">
</p>

<p align="center">
  <a href="https://github.com/RomjanAli0266/WorkForceX-Employee_Management_System">
    <strong>View Repository</strong>
  </a>
</p>

---

## 📌 About the Project

**WorkForceX** is a secure and scalable Employee Management System developed using Java and Spring Boot.

It provides REST APIs for managing employee information with JWT-based authentication, role-based authorization, CRUD operations, searching, pagination, sorting, request validation and centralized exception handling.

The application follows a layered architecture and uses Spring Data JPA and Hibernate for database operations.

## ✨ Key Features

| Feature | Description |
|---|---|
| 🔐 JWT Authentication | Secure user registration and login |
| 🛡️ Role-Based Authorization | ADMIN and USER roles |
| 👨‍💼 Employee Management | Create, read, update and delete employees |
| 🔎 Search & Filtering | Search employees using supported fields |
| 📄 Pagination | Retrieve employee records page by page |
| ↕️ Sorting | Sort employee records |
| ✅ Request Validation | Validate incoming API requests |
| ⚠️ Global Exception Handling | Centralized error handling |
| 📚 Swagger / OpenAPI | API documentation |
| 🗄️ MySQL Integration | Persistent relational database |

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication and Authorization |
| JWT | Token-Based Authentication |
| Spring Data JPA | Database Access |
| Hibernate | Object-Relational Mapping |
| MySQL | Relational Database |
| Maven | Dependency Management |
| Swagger / OpenAPI | API Documentation |
| Postman | API Testing |
| Git & GitHub | Version Control |

## 🏗️ System Architecture

```text
             Client / Postman
                    |
                    v
             Spring Security
                    |
                    v
                JWT Filter
                    |
                    v
             Controller Layer
                    |
                    v
              Service Layer
                    |
                    v
            Repository Layer
                    |
                    v
               MySQL DB
```

## 📂 Project Structure

```text
employee-management/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/employee_management/
│       │       ├── controller/
│       │       ├── service/
│       │       ├── repository/
│       │       ├── entity/
│       │       ├── dto/
│       │       ├── mapper/
│       │       ├── security/
│       │       └── exception/
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
└── README.md
```

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/RomjanAli0266/WorkForceX-Employee_Management_System.git
```

### 2. Open the Project

Open the cloned project in IntelliJ IDEA, Eclipse or VS Code.

### 3. Create MySQL Database

Execute the following SQL query:

```sql
CREATE DATABASE employeedb;
```

### 4. Configure Database Credentials

Open:

`src/main/resources/application.properties`

Configure your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Important:** Replace the placeholder credentials with your local MySQL credentials. Never upload real passwords or JWT secrets to GitHub.

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from your IDE.

**Backend URL:**

```text
http://localhost:8080
```

## 🔐 Authentication APIs

The application uses JWT authentication to secure protected REST endpoints.

### Register User

**Endpoint:**

```http
POST /auth/register
```

**Example Request:**

```json
{
  "username": "admin",
  "password": "password123",
  "role": "ADMIN"
}
```

### Login User

**Endpoint:**

```http
POST /auth/login
```

**Example Request:**

```json
{
  "username": "admin",
  "password": "password123"
}
```

After successful authentication, the server returns a JWT token.

Use the token in protected API requests:

```http
Authorization: Bearer <JWT_TOKEN>
```

## 👨‍💼 Employee REST APIs

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/employees` | Create a new employee |
| GET | `/employees` | Retrieve all employees |
| GET | `/employees/{id}` | Retrieve employee by ID |
| PUT | `/employees/{id}` | Update employee details |
| DELETE | `/employees/{id}` | Delete an employee |

### Pagination & Sorting

Example request:

```http
GET /employees?page=0&size=5&sortBy=id&direction=asc
```

Supported sorting fields include:

- id
- name
- email
- salary
- designation
- department

### Employee JSON Example

```json
{
  "name": "Rahul Kumar",
  "email": "rahul@example.com",
  "salary": 45000,
  "designation": "Software Developer",
  "department": "IT"
}
```

## 🛡️ Security & Authorization

The application implements role-based access control using Spring Security and JWT.

| Role | Permissions |
|---|---|
| ADMIN | Full employee management access |
| USER | Authorized user-level operations |
| Unauthenticated | Public authentication endpoints only |

**HTTP Security Responses:**

- `401 Unauthorized` – Authentication is missing or invalid.
- `403 Forbidden` – Authenticated user does not have sufficient permissions.

## ✅ Request Validation

Incoming requests are validated using Jakarta Bean Validation.

Common validation annotations include:

```java
@NotBlank
@Email
@Positive
```

Invalid requests are handled through centralized global exception handling.

## 📚 API Documentation

Swagger / OpenAPI is integrated for API documentation and testing.

Start the application and access the Swagger UI using the URL configured in the project.

## 🧪 API Testing

REST APIs are tested using **Postman**.

Testing scenarios include:

- User registration and login
- JWT authentication
- Employee creation
- Employee retrieval
- Employee updates
- Employee deletion
- Search and filtering
- Pagination and sorting
- Request validation
- Unauthorized requests
- Role-based authorization

## 💻 Frontend Integration – EmployeeHub

A React + TypeScript frontend named **EmployeeHub** is being developed to interact with the WorkForceX backend.

**Frontend Technologies:**

- React
- TypeScript
- Vite
- TSX

**Development URLs:**

| Application | URL |
|---|---|
| Frontend | `http://localhost:5173` |
| Backend | `http://localhost:8080` |

Frontend screens and integrations will be documented as development progresses.

## 🔮 Future Enhancements

- Employee profile pictures
- Advanced dashboard analytics
- Salary statistics
- Department-wise employee reports
- Excel / PDF employee data export
- Email notifications
- Refresh token implementation
- Docker containerization
- CI/CD with GitHub Actions
- Cloud deployment

## 👨‍💻 Developer

**Romjan Ali**

B.Tech – Computer Science & Engineering

GitHub: [@RomjanAli0266](https://github.com/RomjanAli0266)

---

<p align="center">
  <strong>⭐ If you find WorkForceX useful, consider giving this repository a star!</strong>
</p>

## 📸 Project Screenshots

This section showcases the key features and functionality of WorkForceX.

### 1. Project Structure
Shows the overall Spring Boot project organization and package structure.

![Project Structure](screenshots/project-structure.png)

### 2. Swagger UI – API Documentation
Interactive API documentation for exploring and testing REST endpoints.

![Swagger UI](screenshots/swagger-ui.png)

### 3. Postman – API Testing
Demonstrates API requests and responses using Postman.

![Postman API Testing](screenshots/postman-api.png)

### 4. MySQL Database
Shows the database and employee records stored in MySQL.

![MySQL Database](screenshots/mysql-database.png)

### 5. JWT Authentication
Demonstrates JWT-based authentication and secured API access.

![JWT Authentication](screenshots/jwt-authentication.png)
