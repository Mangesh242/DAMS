# DAMS - Dhanashree Anand Management System

A comprehensive society management system with separate Angular frontend and Spring Boot backend.

## Overview

This repository contains a complete society management solution that helps manage:

1. **Flat Owners** - Admin can add and manage flat owners in the system
2. **Family Members** - Flat owners can add and manage their family members
3. **Tenants** - Flat owners can add and manage their tenants with lease details
4. **Maintenance** - Payment processing and receipt generation
5. **Visitors** - Complete visitor tracking with approval workflow

## Project Structure

```
DAMS/
├── backend/          # Spring Boot REST API
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── frontend/         # Angular Application
│   ├── src/
│   ├── package.json
│   └── README.md
└── README.md
```

## Features

### Admin Module
- Add flat owners to the system
- Manage flat details (flat number, wing, floor)
- Set maintenance amounts
- View all residents and tenants

### Flat Owner Module
- Add family members with relationship details
- Add tenants with lease information
- Pay maintenance and generate receipts
- Approve/reject visitor requests
- View maintenance history

### Visitor Management
- Record visitor details (name, photo, contact, in-time, out-time)
- Specify flat to visit and reason
- Approval workflow for flat owners
- Track visitor entry and exit times

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- Spring Security
- H2 Database (can be replaced with MySQL/PostgreSQL)
- Maven

### Frontend
- Angular 16
- TypeScript
- RxJS
- HttpClient

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+ and npm

### Running the Backend

```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Running the Frontend

```bash
cd frontend
npm install
npm start
```

The application will be available at `http://localhost:4200`

## API Documentation

The backend provides RESTful APIs for all operations. See [backend/README.md](backend/README.md) for detailed API documentation.

## Database

The application uses H2 in-memory database by default for easy testing. You can access the H2 console at:
`http://localhost:8080/h2-console`

For production, configure MySQL or PostgreSQL in `application.properties`.

## Security

The application includes Spring Security configuration. Currently configured to permit all requests for development. Update the security configuration for production use with proper authentication and authorization.

## License

This project is for educational purposes.
