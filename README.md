# User-Management-System
A robust Spring Boot User Management and Task Assignment REST API featuring Spring Security, JWT authentication, Role-Based Access Control (Admin, Manager, User), and an embedded H2 database.

# Spring Boot User Management & Task Assignment System

A secure, production-ready backend application built with **Spring Boot** that handles secure user authentication, role-based access control (RBAC), and task management operations.

## 🚀 Tech Stack
* **Framework:** Spring Boot
* **Security:** Spring Security & JSON Web Tokens (JWT)
* **Data Access:** Spring Data JPA / Hibernate
* **Database:** H2 Database (In-Memory)
* **Utilities:** Lombok, Jakarta Validation
* **Build Tool:** Maven

## 👥 Core Features & Roles
* **Authentication (`/api/auth/**`):** Secure user registration and login with JWT token generation.
* **Admin Role (`/api/admin/**`):** Manage system users, view user lists, update profiles, delete accounts, and assign roles (`Admin`, `Manager`, `User`).
* **Manager Role (`/api/manager/**`):** View registered users and assign/track tasks.
* **User Role (`/api/user/**`):** View personal profiles, check assigned tasks, and update task statuses.
* **Data Initialization:** Automatically seeds default admin, manager, and user accounts on application startup.

## 📊 H2 Database Console
While the application is running, you can inspect the database tables (`USERS`, `ROLES`, `TASKS`, `USER_ROLES`) by visiting:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Username:** `sa`
* **Password:** *(leave blank)*

## 🛠️ Getting Started & Running Locally
1. Clone the repository:
   ```bash
