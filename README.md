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
- H2 In-Memory Database
- Swagger API Docs
- Jwt authentication

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


## 📸 Swagger UI Screenshot

You can view the Swagger UI screenshot here:

![Swagger UI Screenshot](images/Screenshot.jpg)

---

## 🔐 JWT Authentication in Swagger

To access protected APIs (like `/api/v1/orders`, etc.), you must authenticate and provide the **JWT token** in Swagger UI.

### ✅ Steps to Test JWT Auth from Swagger

1. **Start the Application**
   - Run the Spring Boot app.
   - Navigate to: [http://localhost:8888/swagger-ui/index.html](http://localhost:8888/swagger-ui/index.html)

2. **Register or Login**
   - Use `auth/register` or `auth/login` to get your **access token**.

3. **Copy the JWT Token**
   - You will get a response like:

     ```json
     {
       "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     }
     ```

4. **Authorize in Swagger**
   - Click the **Authorize** button at the top-right of Swagger.
   - Enter the token as:
     ```
     Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
     ```

   - Click **Authorize**, then **Close**.

5. **Access Protected APIs**
   - Now you can access any protected routes like:
     - `GET /api/v1/orders/all`
     - `POST /api/v1/orders/add`
     - etc.

---

## 🖼️ Screenshot Example

![JWT Swagger Screenshot](images/swagger-jwt-auth.png)

---

## 🖼️ Screenshot Example

![JWT Swagger Screenshot](screenshots/swagger-jwt-auth.png)
-----------------------------------
🔹 Product Management Endpoints
-------------------------------------
POST   /products           - Add a new product  
GET    /products           - List all products  
GET    /products/{id}      - Get product by ID  

🔹 Order Management Endpoints
-------------------------------------
POST   /orders             - Create a new order  
GET    /orders             - List all orders  

🔹 QR Code Generation Endpoints
-------------------------------------
GET    api/qr/base64         - Get QR code as Base64 string  

## ⚙️ How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/mpost-order-service.git
cd mpost-order-service
