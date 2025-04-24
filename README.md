# mPOS_Order_Service
# 💳 mPOS Order Service Backend

A simple and robust backend for a Mobile Point-of-Sale (mPOS) system built using Java and Spring Boot. This service handles **product management**, **order creation**, and **QR code generation** for order confirmation.

---

## 📌 Features

### ✅ Product Management
- Add new products
- Fetch all products
- Get product by ID

### ✅ Order Management
- Create a new order
- Stock validation and deduction
- Generate unique order ID
- Return order summary with product details
- List all orders

### ✅ QR Code Generation
- Generate a QR code with:
  - Order ID
  - Total amount
  - Customer name
- Return as Base64 string and downloadable image

### 🧪 Bonus Features (Optional)
- JWT-based authentication (can be extended)
- H2 In-Memory Database
- Swagger API Docs
- Docker-ready (optional)

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- Swagger (Springdoc OpenAPI)
- ZXing (QR Code generation)
- Maven

---

## ⚙️ How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/mpost-order-service.git
cd mpost-order-service
