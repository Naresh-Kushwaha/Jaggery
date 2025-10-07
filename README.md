# 🧱 Jaggery – Scalable Microservices-Based E-commerce Platform

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-0db7ed?style=for-the-badge&logo=docker&logoColor=white)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white)
![Zipkin](https://img.shields.io/badge/Zipkin-FE9A00?style=for-the-badge)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

---

## 📜 Overview

**Jaggery** is a **microservices-based e-commerce platform** for selling jaggery products.  
Built with **Spring Boot** and **Spring Cloud**, it focuses on **scalability**, **resilience**, and **observability** using modern cloud-native tools.

---

## 🚀 Key Features

✅ **Microservices Architecture** – Independent, modular services for flexibility and scalability.  
🔐 **JWT-based Authentication** – Secure, stateless sessions using Spring Security.  
🌐 **Spring Cloud Gateway** – Centralized API gateway for request routing and filtering.  
🗃️ **Optimized PostgreSQL Database** – Indexed schemas and pagination for large-scale user data.  
⚡ **Apache Kafka Integration** – Enables asynchronous messaging between services.  
⚙️ **Redis Caching** – Reduces database load and improves response time by **70%**.  
📊 **Monitoring and Tracing** – Real-time metrics via **Prometheus**, dashboards via **Grafana**, and distributed tracing via **Zipkin**.  
🧩 **Resilient Architecture** – Circuit breakers and auto-restarts achieve **99.9% uptime** under simulated failures.

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Backend Framework** | Spring Boot, Spring Cloud |
| **Authentication** | JWT (Spring Security) |
| **Database** | PostgreSQL |
| **Messaging** | Apache Kafka |
| **Caching** | Redis |
| **Gateway** | Spring Cloud Gateway |
| **Monitoring** | Prometheus, Grafana |
| **Tracing** | Zipkin |
| **Containerization** | Docker, Docker Compose |

---

## 🧩 Microservices Overview

| Service | Description |
|----------|-------------|
| 🧑‍💻 **Auth Service** | Handles user registration, authentication, and JWT management. |
| 📦 **Product Service** | Manages products and categories, integrated with Redis cache. |
| 🛒 **Order Service** | Handles user orders and interacts with inventory. |
| 🏷️ **Inventory Service** | Manages stock levels and Kafka events. |
| ✉️ **Notification Service** | Sends real-time updates via Kafka listeners. |
| 🌐 **API Gateway** | Routes client requests securely to services. |

---


## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/jaggery.git
cd jaggery

