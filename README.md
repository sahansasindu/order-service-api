# Order Service API

## Overview

The **Order Service** is responsible for managing all Order-related operations in the system.

It is built using **Spring boot** and exposes RESTful APIs for handling
- order creation
- order updates
- order tracking
- order status management

The service uses **MySQL** as its database.

---
***E-Commerce System***

![Architecture Diagram](https://github.com/user-attachments/assets/8cdca14d-77a1-4df4-85a5-5f2f1ffe3e50)

---

## Features

- Product management
- Category management
- Shopping cart functionality
- Product reviews and ratings
- Discount handling
- Bookmark/favorite products
- order management.
- payment gateway.
- user handle.


## System Architecture

This project follows a **microservices architecture** with the following core components:

### Keycloak(https://www.keycloak.org/documentation)
Used as an **Identity and Access Management (IAM)** solution.

- Handles authentication and authorization
- Provides secure login and token-based access

<img width="1659" height="757" alt="image" src="https://github.com/user-attachments/assets/1caca8f1-bb11-4b7c-82d2-f18abb8a9881" />


---

### Eureka Server (https://github.com/sahansasindu/eureka-service-api)
Used as a **Service Registry**.

- Registers all microservices
- Enables dynamic service discovery

---

### API Gateway (https://github.com/sahansasindu/quickcart_gateway)
Acts as the **entry point** for all client requests.

- Routes requests to appropriate services
- Integrates with Keycloak for security
- Handles cross-cutting concerns

---

## Frontend Clients

- **User Client (Angular)** → Used by normal users (https://github.com/sahansasindu/quickcart_client)
- **Admin Client (React)** → Used by administrators (https://github.com/sahansasindu/Quick-Cart-Admin)

---

## API'S

- Order Service API (https://github.com/sahansasindu/order-service-api)
- User Service APT  (https://github.com/sahansasindu/user-service-api)
- Product Service API (https://github.com/sahansasindu/product-service-api)
- Util Service API

---




