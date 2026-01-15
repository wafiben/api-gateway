# API Gateway - Docker Setup

This project contains the **API Gateway** for the microservices architecture.  
This guide explains **how to build, run, and push** the Docker image.

---

## 1️⃣ Prerequisites

- Docker installed: [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
- Java 17 (optional locally, only for development)
- Maven (optional locally, only for development)

---

## 2️⃣ Build Docker Image

From the root of the project (where `Dockerfile` is located):
```bash
docker build -t api-gateway .
```

---

## 3️⃣ Verify Image is Ready
```bash
docker images
```

---

## 4️⃣ Run Your Image
```bash
docker run -p 9000:9000 api-gateway
```

---

SPRING_APPLICATION_NAME