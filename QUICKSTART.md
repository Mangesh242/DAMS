# Quick Start Guide

This guide will help you get the Society Management System up and running quickly.

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Node.js 16+** and npm (for frontend, when implemented)

## Backend Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Mangesh242/DAMS.git
cd DAMS
```

### 2. Build the Backend

```bash
cd backend
mvn clean install
```

### 3. Run the Backend

```bash
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### 4. Verify Installation

Open your browser and navigate to:

- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:societydb`
  - Username: `sa`
  - Password: (leave empty)

### 5. Test the API

```bash
# Get all flat owners
curl http://localhost:8080/api/flat-owners

# Get all visitors
curl http://localhost:8080/api/visitors

# Get all maintenance records
curl http://localhost:8080/api/maintenance
```

## Sample Data

The application automatically initializes with sample data:

### Flat Owners
- **John Doe** - Flat 101, Wing A
- **Jane Smith** - Flat 102, Wing A

### Family Members
- Mary Doe (John's spouse)
- Tom Doe (John's son)

### Tenants
- Bob Johnson (Tenant at Flat 102)

### Maintenance Records
- Payment for Flat 101 (Completed)
- Payment for Flat 102 (Completed)

### Visitors
- Alice Brown - Approved visitor for Flat 101
- Charlie Wilson - Pending visitor for Flat 102

## Running Tests

```bash
cd backend
mvn test
```

## API Documentation

Detailed API documentation is available in [API_DOCUMENTATION.md](../API_DOCUMENTATION.md)

## Common Operations

### Create a New Flat Owner

```bash
curl -X POST http://localhost:8080/api/flat-owners \
  -H "Content-Type: application/json" \
  -d '{
    "username": "new.owner",
    "password": "password123",
    "firstName": "New",
    "lastName": "Owner",
    "email": "newowner@example.com",
    "phoneNumber": "9999999999",
    "flatNumber": "201",
    "wing": "B",
    "floor": 2,
    "maintenanceAmount": 6000.0
  }'
```

### Add a Family Member

```bash
curl -X POST http://localhost:8080/api/family-members \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Family",
    "lastName": "Member",
    "relationship": "Spouse",
    "phoneNumber": "8888888888",
    "email": "family@example.com",
    "age": 30,
    "flatOwnerId": 1
  }'
```

### Record a Visitor

```bash
curl -X POST http://localhost:8080/api/visitors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "New Visitor",
    "phoneNumber": "7777777777",
    "inTime": "2025-10-15T10:00:00",
    "flatNumber": "101",
    "flatOwnerId": 1,
    "reason": "Delivery"
  }'
```

### Approve a Visitor

```bash
curl -X POST http://localhost:8080/api/visitors/1/approve \
  -H "Content-Type: application/json" \
  -d '{
    "remarks": "Approved"
  }'
```

### Pay Maintenance

```bash
curl -X POST http://localhost:8080/api/maintenance \
  -H "Content-Type: application/json" \
  -d '{
    "flatOwnerId": 1,
    "amount": 5000.0,
    "paymentDate": "2025-10-15",
    "paymentMode": "Online",
    "transactionId": "TXN789012",
    "status": "COMPLETED"
  }'
```

## Troubleshooting

### Port 8080 Already in Use

If port 8080 is already in use, you can change it in `backend/src/main/resources/application.properties`:

```properties
server.port=8081
```

### Database Issues

If you encounter database issues, delete the H2 database file and restart:

```bash
cd backend
rm -rf target/
mvn spring-boot:run
```

### Build Failures

If the build fails, try cleaning the Maven cache:

```bash
mvn clean install -U
```

## Next Steps

1. Explore the API using the provided endpoints
2. Check out the comprehensive [API Documentation](../API_DOCUMENTATION.md)
3. Review the sample data in the H2 console
4. Implement the Angular frontend (coming soon)

## Support

For issues or questions, please create an issue in the GitHub repository.
