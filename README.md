# Edocs Backend

## Electronic Document Management and Signature Request System

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-brightgreen)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Status](https://img.shields.io/badge/Project-Completed-success)

## 1. Student Information

| Field | Information |
| --- | --- |
| **Student Name** | Ntwari Ashimwe Fiacre |
| **Student ID** | 27438 |
| **Course** | INSY 8322 WEB TECHNOLOGY AND INTERNET |
| **Academic Year** | 2026–2027 |
| **Project** | Edocs – Mid-Term Examination |

## 2. How to Run the Application

### Prerequisites

Make sure you have installed:

- Java Development Kit (JDK 17 or the version configured in the project).
- PostgreSQL.
- Maven (or use the Maven Wrapper included in the project).
- IntelliJ IDEA, Eclipse, or another Java IDE.
- Postman for API testing.

### Installation

**Step 1 – Clone the repository**

```bash
git clone https://github.com/0122345/edocs_backend.git
```

**Step 2 – Open the project**

Open the cloned project in your preferred Java IDE.

**Step 3 – Create the database**

```sql
CREATE DATABASE edocs_db;
```

**Step 4 – Configure the database**

Update the PostgreSQL username and password in `application.properties`.

**Step 5 – Run the application**

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, run `EdocsApplication.java` from your IDE.

**Step 6 – Access the API**

By default, the application runs at:

```text
http://localhost:8080
```
