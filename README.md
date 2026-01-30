# 🏥 MediCare Hospital Management System

MediCare Hospital Management System is a **microservices-based web application** for managing hospital operations. It includes **doctor management**, **patient management**, **service discovery**, and a **modern frontend**. This project is ideal for learning **Spring Boot microservices**, **React**, and **full-stack integration**.

---

## 📌 Project Architecture

The system uses **microservices architecture** with:

- **Eureka Server** – Service discovery  
- **Config Server** – Centralized configuration  
- **API Gateway** – Routing and security  
- **Admin Server** – Monitoring services  
- **Doctor API (Producer Service)** – CRUD for doctors  
- **Patient API (Consumer Service)** – CRUD for patients, consumes Doctor service  

The **React frontend** communicates with the backend **only through API Gateway**.

---

## 🛠️ Tech Stack

**Backend:**  
- Java 17 / 20  
- Spring Boot  
- Spring Cloud (Eureka, Config, Gateway)  
- Spring Data JPA  
- Maven  
- REST APIs  

**Frontend:**  
- React (Vite)  
- Tailwind CSS  
- Axios for API calls  

**Other Tools:**  
- Git & GitHub  
- VS Code (Frontend) / Eclipse (Backend)

---

## 📂 Project Structure

MediCare-Hospital-Management/
│
├── MediCareMS-EurekaServer
├── MediCareMS-ConfigServer
├── MediCareMS-APIGateWay
├── MediCareMS-AdminServer
├── MediCareMS-DoctorAPI-ProducerApp
├── MediCareMS-PatientAPI-ConsumerApp
│
├── medicare-ui
└── README.md



---

## ✨ Key Features

- Microservices architecture with service discovery  
- Centralized configuration with Config Server  
- API Gateway routing  
- Inter-service communication using REST  
- CRUD operations for Doctors and Patients  
- React-based responsive frontend  
- Centralized exception handling  
- Monorepo Git structure

---

## ▶️ How to Run

### 🔹 Backend Services (in order)
1. Start **Eureka Server**  
2. Start **Config Server**  
3. Start **API Gateway**  
4. Start **Admin Server**  
5. Start **Doctor API (Producer)**  
6. Start **Patient API (Consumer)**  

> All services will register automatically with Eureka.

### 🔹 Frontend
```bash
cd medicare-ui
npm install
npm run dev

Frontend will run at http://localhost:5173

🧠 Learning Outcomes

Understanding of microservices architecture

Service discovery and API Gateway

Centralized configuration with Config Server

CRUD operations with Spring Data JPA

Frontend-backend integration with React

Git monorepo management

👨‍💻 Author

Rahul Shetloor
Java | Spring Boot | Microservices | React





