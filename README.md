🔐 Spring Security + API Gateway Demo — Summary

This project demonstrates a microservices security setup using:

Spring Cloud Gateway — routes client requests to backend services

Spring Boot 3 Security Service — authenticates users and loads credentials from MySQL

JWT authentication — protects secured APIs while leaving public APIs open

A Postman collection is included to test:

Public endpoints — accessible without authentication

Secured endpoints — require a valid JWT token in the Authorization: Bearer <token> header

🏗️ High-Level Flow

Client logs in and receives a JWT

Client calls API Gateway with JWT

Gateway validates the token

Forwarded request reaches the secured service

⚙️ Tech Stack

Spring Boot 3 • Spring Security • Spring Cloud Gateway • MySQL • JWT • Maven

🚀 How to Run

Configure DB → build apps → start services → import Postman collection → test public & secured APIs.
