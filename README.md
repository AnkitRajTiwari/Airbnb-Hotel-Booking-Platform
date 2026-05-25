# 🏨 AirBnb Clone - Hotel Booking Platform

A full-featured hotel booking REST API built with Spring Boot, featuring JWT authentication, Stripe payment integration, and comprehensive hotel management capabilities.

## 📋 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [Future Enhancements](#future-enhancements)

## ✨ Features

### User Management
- 🔐 JWT-based authentication with refresh tokens
- 👤 User registration and login
- 📝 Profile management with role-based access control (HOTEL_MANAGER, GUEST)
- 🔄 Automatic token refresh mechanism

### Hotel Management (Admin)
- 🏨 Create, update, and delete hotels
- ✅ Hotel activation/deactivation
- 📊 Booking reports and analytics by date range

### Room & Inventory Management
- 🛏️ Room type management (Single, Double, Suite, etc.)
- 💰 Dynamic pricing strategies (Base, Seasonal, Demand-based)
- 📅 Date-wise inventory and availability tracking
- 🔄 Bulk inventory updates

### Booking System
- 🔍 Smart hotel search with filters (city, dates, Total count)
- 💳 Secure payment processing with Stripe
- 👥 Multi-guest booking support
- 📈 Real-time booking status tracking
- ❌ Booking cancellation with automatic refunds
- 📋 Booking history for users

### Payment Integration
- 💳 Stripe payment gateway integration
- 🔔 Webhook handling for payment confirmation
- 🔒 Secure payment session management
- ♻️ Automatic refund processing

## 🛠 Tech Stack

### Backend
- **Framework**: Spring Boot 4.0.2
- **Language**: Java 21
- **Build Tool**: Maven 3.8+

### Database & ORM
- **Database**: PostgreSQL 15+
- **ORM**: Spring Data JPA / Hibernate
- **Migrations**: Hibernate DDL Auto (development)

### Security
- **Authentication**: JWT (JSON Web Tokens)
- **Library**: Spring Security 6.x + jjwt 0.12.6
- **Password Encryption**: BCrypt

### Payment
- **Payment Gateway**: Stripe API v28.2.0
- **Features**: Payment intents, webhooks, refunds

### API Documentation
- **Framework**: SpringDoc OpenAPI 3 (Swagger)
- **Version**: 2.8.3
- **UI**: Swagger UI

### Additional Libraries
- **Lombok**: Reduces boilerplate code
- **ModelMapper**: DTO mapping
- **SLF4J + Logback**: Logging

### Design Patterns
- **MVC Pattern**: Separation of concerns
- **Repository Pattern**: Data access abstraction
- **DTO Pattern**: Data transfer between layers
- **Strategy Pattern**: Dynamic pricing strategies
- **Dependency Injection**: Spring IoC container

## 🚀 Getting Started
### Prerequisites
- **Java 21** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **PostgreSQL 15+** ([Download](https://www.postgresql.org/download/))
- **Maven 3.8+** (included via Maven Wrapper)
- **Postman** (optional, for API testing)


## 🔌 API Endpoints

### 🔐 Authentication

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/auth/signup` | Create new user account | ❌ |
| `POST` | `/api/v1/auth/login` | Login and receive JWT token | ❌ |
| `POST` | `/api/v1/auth/refresh` | Refresh expired access token | ❌ |

---

### 🏨 Hotels (Admin)
> **Required Role:** `HOTEL_MANAGER`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/admin/hotels` | Create new hotel | ✅ |
| `GET` | `/api/v1/admin/hotels` | Get all owned hotels | ✅ |
| `GET` | `/api/v1/admin/hotels/{id}` | Get hotel details by ID | ✅ |
| `PUT` | `/api/v1/admin/hotels/{id}` | Update hotel information | ✅ |
| `DELETE` | `/api/v1/admin/hotels/{id}` | Delete hotel | ✅ |
| `PATCH` | `/api/v1/admin/hotels/{id}/activate` | Activate/deactivate hotel | ✅ |
| `GET` | `/api/v1/admin/hotels/{id}/bookings` | Get all bookings for hotel | ✅ |
| `GET` | `/api/v1/admin/hotels/{id}/reports` | Generate booking reports | ✅ |

---

### 🛏️ Rooms (Admin)
> **Required Role:** `HOTEL_MANAGER`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/admin/rooms` | Create new room type | ✅ |
| `PUT` | `/api/v1/admin/rooms/{id}` | Update room details | ✅ |
| `DELETE` | `/api/v1/admin/rooms/{id}` | Delete room type | ✅ |

---

### 📦 Inventory (Admin)
> **Required Role:** `HOTEL_MANAGER`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `PUT` | `/api/v1/admin/inventory` | Update room availability for dates | ✅ |

---

### 🔍 Hotel Search (Public)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/hotels/search` | Search available hotels by criteria | ❌ |
| `GET` | `/api/v1/hotels/{id}/info` | Get detailed hotel information | ❌ |

---

### 🎫 Bookings (Customer)
> **Required Role:** `CUSTOMER`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/bookings` | Initialize new booking | ✅ |
| `POST` | `/api/v1/bookings/{id}/guests` | Add guest details to booking | ✅ |
| `POST` | `/api/v1/bookings/{id}/pay` | Initiate Stripe payment | ✅ |
| `GET` | `/api/v1/bookings/{id}/status` | Check booking status | ✅ |
| `GET` | `/api/v1/bookings/my` | Get all my bookings | ✅ |
| `DELETE` | `/api/v1/bookings/{id}` | Cancel booking | ✅ |

---

### 👤 User Profile
> **Required Role:** `CUSTOMER` or `HOTEL_MANAGER`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/users/profile` | Get current user profile | ✅ |
| `PUT` | `/api/v1/users/profile` | Update profile information | ✅ |

---

### 🔔 Webhooks

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/webhooks/stripe` | Stripe payment webhook handler | ⚠️* |

*Authenticated via Stripe signature verification

---

## 🔐 Environment Variables

### Required Configuration

| Variable | Description | Example |
|----------|-------------|---------|
| DB_URL | Database URL | jdbc:postgresql://localhost:5432/airbnb |
| DB_USERNAME | Database username | postgres |
| DB_PASSWORD | Database password | your_password |
| JWT_SECRET | JWT secret key | your_secret_key |
| STRIPE_SECRET_KEY | Stripe API key | sk_test_xxxxx |
| STRIPE_WEBHOOK_SECRET | Stripe webhook secret | whsec_xxxxx |

## 📊 Common Response Format

All API responses follow this standardized format:

**Success Response:**
```json
{
  "timeStamp": "2024-03-08T10:30:00",
  "data": {
    // Response data here
  },
  "error": null
}
```

## 🧪 Testing
### Swagger UI
Visit: http://localhost:8080/api/v1/swagger-ui.html

### Postman
Import collection from: `postman/AirBnb Clone.postman_collection.json`


## 🚧 Future Enhancements
- 🧪 **Testing & DevOps**: Unit tests, Docker, CI/CD, database migrations
- ⚡ **Performance**: Redis caching, rate limiting, query optimization, pagination
- ✨ **Features**: Reviews/ratings, email notifications, advanced search, cloud image storage
- 🔒 **Security**: 2FA, OAuth2 social login, audit logging
- 📊 **Monitoring**: Prometheus, centralized logging, error tracking, analytics dashboard

### This Project demonstrates:
- ✅ RESTful API design & Spring Boot expertise
- ✅ Database design with PostgreSQL + JPA
- ✅ Security implementation (JWT + Spring Security)
- ✅ Payment integration (Stripe)
- ✅ Clean architecture & production-ready patterns
