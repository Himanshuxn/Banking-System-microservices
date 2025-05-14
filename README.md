# 🏦 Digital Bank Microservices

Welcome to the **Digital Bank Microservices** project!  
This is a modern, scalable, and secure banking system built with **Java**, **Spring Boot**, and **microservices** technologies. The system simulates core banking functionalities and integrates **machine learning**, **event-driven architecture**, and **real-time monitoring**.

## 🚀 Features

### Core Services
- **Customer Service**: Manages customer registration and profile.
- **Account Service**: Manages accounts and operations (deposit, withdrawal).
- **Transaction Service**: Handles all financial transactions.
- **Loan Service**: Manages loans and repayment schedules.
- **Fraud Detection Service**: Detects fraud using machine learning (TensorFlow/Weka).
- **Credit Scoring Service**: Evaluates customer credit dynamically.
- **Notification Service**: Sends email/SMS/push notifications.

### 🔧 Technologies
- **Spring Boot**, **Spring Cloud** (Config, Gateway, Eureka)
- **Spring Security** + JWT
- **Kafka/RabbitMQ** for messaging
- **TensorFlow/Weka** for ML
- **PostgreSQL**, **MongoDB**
- **Docker**, **ELK**, **Prometheus**, **Grafana**

## 🧱 Architecture
Each microservice is:
- Independently deployable
- Communicates via REST or Kafka
- Maintains its own DB (PostgreSQL/MongoDB)
