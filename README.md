# SmartBiz Backend

SmartBiz Backend is the REST API service for the **SmartBiz: AI-Powered Business Management Suite for SMEs**.
It provides secure APIs for managing businesses, users, products, sales, expenses, AI requests, and subscriptions.

---

## 🚀 Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Spring Security
* JWT Authentication
* Lombok
* OpenAI API Integration
* Maven

---

## 🏗️ Architecture

The backend follows a layered architecture:

Controller → Service → Repository → Entity → Database

It ensures:

* Separation of concerns
* Maintainability
* Scalability
* Clean code structure

---

## 📁 Project Structure

```
com.smartbiz.smartbiz_backend
│
├── config          # Security & JWT configuration
├── controller      # REST API controllers
├── service         # Business logic layer
├── repository      # JPA repositories
├── entity          # Database entities
├── dto             # Request & Response DTOs
├── exception       # Global exception handling
└── SmartbizBackendApplication.java
```

---

## 🔐 Authentication

The system uses **JWT-based authentication**.

### Flow:

1. User registers or logs in
2. Server generates JWT token
3. Client sends token in Authorization header
4. Backend validates token for protected APIs

Authorization Header Format:

```
Authorization: Bearer <your_token>
```

---

## 🗄️ Database Design

The backend supports a multi-tenant architecture.

Main Entities:

* Business
* User
* Product
* Customer
* Supplier
* Sale
* InvoiceItem
* Expense
* AIRequest
* Subscription
* Admin

Each Business has its own users, products, sales, and AI usage data.

---

## 🤖 AI Integration

The system integrates with OpenAI API to provide:

* Business performance insights
* Email generation
* Marketing post generation
* Invoice explanations

Every AI interaction is logged in the `AIRequest` table for:

* Usage tracking
* Billing
* Analytics

---

## 🛠️ Setup Instructions

### 1️⃣ Clone Repository

```
git clone https://github.com/your-username/smartbiz-backend.git
cd smartbiz-backend
```

---

### 2️⃣ Configure Database

Create MySQL database:

```
CREATE DATABASE smartbiz_db;
```

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/smartbiz_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run Application

Using Maven:

```
mvn spring-boot:run
```

Or run `SmartbizBackendApplication.java` from IDE.

Server will start at:

```
http://localhost:8080
```

---

## 📡 API Base URL

```
http://localhost:8080/api
```

Example endpoints:

* POST /api/auth/login
* POST /api/products
* GET /api/products
* POST /api/sales
* POST /api/ai/generate

---

## 🧪 Testing APIs

Use:

* Postman
* Thunder Client
* Swagger (if enabled)

---

## 🌍 Deployment

The backend is designed to be deployed on:

* AWS EC2
* Docker containers
* Any cloud VM

---

## 👨‍💻 Authors

SmartBiz Development Team
AFSD Intake – Final Project

---

## 📄 License

This project is developed for academic purposes.
