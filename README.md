# Jewelry Inventory Management System

A REST API backend for managing jewelry inventory, inspired by real-world operations at Tripura Jewellers, Dungarpur. Built with Spring Boot, JPA, and MySQL.

## Tech Stack

- Java 21
- Spring Boot 4.1
- Spring Data JPA + Hibernate 7
- MySQL 8
- Maven
- Lombok

## Features

- Category management with making charge percentage per category
- Product management with automatic total price calculation
- Soft delete — records are never permanently removed, supporting audit trail
- Input validation with meaningful error messages
- Custom exception handling with structured error responses
- RESTful API design with proper HTTP status codes

## Price Calculation Logic

Total Price = Weight (grams) × Rate per gram × (1 + Making Charge % / 100)

Example: 10g gold ring at ₹6000/g with 5% making charge = ₹63,000

## API Endpoints

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/categories | Create new category |
| GET | /api/categories | Get all active categories |
| GET | /api/categories/{id} | Get category by id |
| PUT | /api/categories/{id} | Update category |
| DELETE | /api/categories/{id} | Soft delete category |

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/products | Create new product |
| GET | /api/products | Get all active products |
| GET | /api/products/{id} | Get product by id |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Soft delete product |

## Project Structure
src/main/java/com/tripura/jewelry_inventory/
├── controller        # REST endpoints
├── dto
│   ├── request       # Incoming request data
│   └── response      # Outgoing response data
├── entity            # JPA entities mapped to database tables
├── exception         # Custom exceptions and global handler
├── repository        # Spring Data JPA repositories
└── service           # Business logic layer

## Running Locally

**Prerequisites:** Java 21, MySQL 8, Maven

**1. Clone the repository**
```bash
git clone https://github.com/dishantpan/jewelry-inventory-system.git
cd jewelry-inventory-system
```

**2. Create MySQL database**
```sql
CREATE DATABASE jewelry_inventory;
```

**3. Configure application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jewelry_inventory
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Run the application**
```bash
mvn spring-boot:run
```

API will be available at `http://localhost:8080`

## Sample Requests

**Create Category**
```json
POST /api/categories
{
    "categoryName": "Gold",
    "description": "24 carat gold jewelry",
    "makingChargePercentage": 5.0
}
```

**Create Product**
```json
POST /api/products
{
    "productName": "Gold Ring",
    "categoryId": 1,
    "weightInGrams": 10.000,
    "ratePerGram": 6000.00
}
```

**Response includes calculated total price**
```json
{
    "productId": 1,
    "productName": "Gold Ring",
    "categoryName": "Gold",
    "weightInGrams": 10.000,
    "ratePerGram": 6000.00,
    "totalPrice": 63000.00
}
```

## Author

Dishant Panchal — Java Backend Developer