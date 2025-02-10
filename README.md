# Grocery Booking API

A simple Grocery Booking API built with Spring Boot, Spring Security, and PostgreSQL.
It supports user registration, login, role-based access control, and grocery management.

## Prerequisites

- Java 11+
- Maven or Gradle
- PostgreSQL
- Postman (for testing)

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/shaikh-vms/qp-assessment.git
```

### 2. PostgreSQL Setup

1. **Create Roles**:  
   ```sql
   INSERT INTO t_user_role (name) VALUES ('USER');
   INSERT INTO t_user_role (name) VALUES ('ADMIN');
   ```
### 3. Run the Application

#### Using Maven:

```bash
mvn spring-boot:run
```

#### Using Gradle:

```bash
gradle bootRun
```

The app will run at `http://localhost:8080`.

## Security Configuration

### Roles:
- **ADMIN**: Can manage groceries (add, update, delete).
- **USER**: Can view and order groceries.

### User Registration
Register users via `POST /auth/register` with `username`, `password`, and `role` parameters:
```bash
http://localhost:8080/auth/register?username=user&password=user&role=USER
```

### Admin Endpoints
- **Add grocery items**: `POST /admin/grocery/add`
- **View grocery items**: `GET /admin/grocery/view`
- **Update grocery items**: `PUT /admin/grocery/update/{id}`
- **Delete grocery items**: `DELETE /admin/grocery/remove/{id}`

### User Endpoints
- **View groceries**: `GET /groceries`
- **Order groceries**: `POST /groceries/order`

### Example Requests

#### Admin: Add Grocery
- **URL**: `POST /admin/grocery/add`
- **Auth**: Basic Auth (Username: `admin`, Password: `admin`)
- **Body**: JSON list of groceries to add.

#### User: View Groceries
- **URL**: `GET /groceries`
- **Auth**: Basic Auth (Username: `user`, Password: `user`)
---

For further questions or improvements, feel free to contribute or raise issues!
