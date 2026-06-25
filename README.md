# Student Management System

A full-stack Student Management System built using **Spring Boot**, **MongoDB Atlas**, **Spring Security**, **JWT Authentication**, and **Tailwind CSS**.

The application provides secure authentication, role-based access control, student management, user approval workflows, dashboard analytics, and separate Admin and Student portals.

---

## Features

### Authentication & Security

* JWT-based Authentication
* BCrypt Password Hashing
* Spring Security Integration
* Protected REST APIs
* Role-Based Access Control (RBAC)
* Stateless Authentication

### User Management

* User Registration
* User Login
* Profile Management
* Change Password
* User Approval Workflow
* Role Assignment

### Student Management

* Create Students
* View Students
* Update Students
* Delete Students
* Search Students by Name
* Filter Students by Branch
* Student Statistics

### Admin Portal

* Dashboard Analytics
* User Management
* Approval Requests
* Role Management
* Student CRUD Operations

### Student Portal

* Student Dashboard
* Student Directory
* Profile Management
* Password Change

### Validation & Error Handling

* Jakarta Bean Validation
* Global Exception Handling
* Clean API Responses
* Input Validation

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data MongoDB
* JWT (JJWT)
* Maven
* Jakarta Validation

### Database

* MongoDB Atlas

### Frontend

* HTML5
* JavaScript (ES6)
* Tailwind CSS

---

## Architecture

```text
Frontend (HTML + JavaScript)
            |
            v
       Controllers
            |
            v
         Services
            |
            v
      Repositories
            |
            v
      MongoDB Atlas

JWT Filter
   |
   +--> Validates every protected request
```

### Layered Architecture

| Layer      | Responsibility                    |
| ---------- | --------------------------------- |
| Controller | Handles HTTP Requests & Responses |
| Service    | Business Logic                    |
| Repository | Database Operations               |

---

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/project/
    │       ├── controller/
    │       ├── service/
    │       ├── repository/
    │       ├── model/
    │       ├── dto/
    │       ├── config/
    │       ├── util/
    │       └── exception/
    │
    └── resources/
        └── application.properties

frontend/
├── index.html
├── register.html
├── pending.html
├── admin-dashboard.html
├── admin-students.html
├── users.html
├── approvals.html
├── student-dashboard.html
├── students.html
└── profile.html
```

---

## API Endpoints

### Authentication APIs

| Method | Endpoint                    | Access        |
| ------ | --------------------------- | ------------- |
| POST   | `/api/auth/register`        | Public        |
| POST   | `/api/auth/login`           | Public        |
| GET    | `/api/auth/me`              | Authenticated |
| PUT    | `/api/auth/change-password` | Authenticated |

### Student APIs

| Method | Endpoint                    | Access         |
| ------ | --------------------------- | -------------- |
| GET    | `/students`                 | Admin, Student |
| GET    | `/students/{id}`            | Admin, Student |
| POST   | `/students`                 | Admin          |
| PUT    | `/students/{id}`            | Admin          |
| DELETE | `/students/{id}`            | Admin          |
| GET    | `/students/search?name=`    | Admin, Student |
| GET    | `/students/branch/{branch}` | Admin, Student |
| GET    | `/students/stats`           | Admin, Student |

### Admin APIs

| Method | Endpoint                          | Access |
| ------ | --------------------------------- | ------ |
| GET    | `/api/admin/users`                | Admin  |
| GET    | `/api/admin/users?status=pending` | Admin  |
| PUT    | `/api/admin/users/{id}/approve`   | Admin  |
| DELETE | `/api/admin/users/{id}/reject`    | Admin  |
| PUT    | `/api/admin/users/{id}/role`      | Admin  |

---

## User Roles

### ADMIN

* Manage Students
* Manage Users
* Approve Registrations
* Change User Roles
* Access Admin Dashboard

### STUDENT

* View Students
* Access Student Dashboard
* Manage Profile
* Change Password

### PENDING

* Await Admin Approval
* Cannot Access Protected Resources

---

## Validation Rules

| Field    | Validation    |
| -------- | ------------- |
| Name     | Required      |
| Email    | Valid Email   |
| Year     | 1 - 4         |
| CGPA     | 0.0 - 10.0    |
| Password | BCrypt Hashed |

---

## Dashboard Statistics

The dashboard provides:

* Total Students
* Average CGPA
* Students Per Branch
* Students Per Year
* Pending Approvals
* Total Users
* Recent Activity

---

## Setup & Installation

### Prerequisites

* Java 17+
* Maven
* MongoDB Atlas Account
* Git

### Clone Repository

```bash
git clone https://github.com/your-username/student-management-system.git

cd student-management-system
```

### Configure MongoDB

Add your MongoDB URI in:

```properties
spring.data.mongodb.uri=YOUR_MONGODB_URI
```

### Configure JWT

```properties
jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```

---

## Security Features

* JWT Authentication
* BCrypt Password Encryption
* Authorization Filters
* Role-Based Access Control
* Protected Endpoints
* Approval-Based Registration
* Environment Variable Support

---

## Testing Checklist

### Authentication

* Register User
* Login User
* Change Password
* Invalid Credentials
* Pending User Access

### Student Management

* Add Student
* Update Student
* Delete Student
* Search Student
* Pagination

### Security

* Unauthorized Access
* Invalid JWT Token
* Role Restrictions
* Expired Token Handling

---

## Future Improvements

* Docker Support
* Swagger/OpenAPI Documentation
* Redis Caching
* Unit Tests
* Integration Tests
* Email Verification
* Password Reset
* CSV Export
* WebSocket Notifications

---

## Screenshots

Add screenshots after deployment:

```md
![Login](screenshots/login.png)

![Admin Dashboard](screenshots/admin-dashboard.png)

![Student Management](screenshots/students.png)

![Profile](screenshots/profile.png)
```

---

## Interview Talking Points

* 3-Layer Architecture (Controller → Service → Repository)
* JWT Authentication & Authorization
* Spring Security Integration
* MongoDB Atlas Cloud Database
* Role-Based Access Control
* Global Exception Handling
* Validation with Jakarta Annotations
* Pagination & Search APIs
* Approval Workflow
* Secure Password Hashing using BCrypt

---

## Future Enhancements

* Docker Containerization
* Redis Caching
* Rate Limiting
* Audit Logs
* Swagger API Documentation
* CI/CD Pipeline
* Cloud Deployment (AWS/Azure)

---

## License

This project was developed for learning, portfolio, and interview preparation purposes.
