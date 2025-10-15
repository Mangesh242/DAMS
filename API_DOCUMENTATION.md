# API Documentation

This document provides detailed information about the REST APIs available in the Society Management System.

## Base URL

```
http://localhost:8080/api
```

## Authentication

Currently, the API is configured to permit all requests for development purposes. For production, implement proper authentication using JWT or OAuth2.

---

## Flat Owner Management

### Create Flat Owner

**POST** `/flat-owners`

Creates a new flat owner in the system (Admin function).

**Request Body:**
```json
{
  "username": "john.doe",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "flatNumber": "101",
  "wing": "A",
  "floor": 1,
  "maintenanceAmount": 5000.0
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "username": "john.doe",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "flatNumber": "101",
  "wing": "A",
  "floor": 1,
  "maintenanceAmount": 5000.0,
  "active": true
}
```

### Get All Flat Owners

**GET** `/flat-owners`

Returns a list of all flat owners.

**Response:** `200 OK`

### Get Flat Owner by ID

**GET** `/flat-owners/{id}`

Returns details of a specific flat owner.

**Response:** `200 OK`

### Update Flat Owner

**PUT** `/flat-owners/{id}`

Updates flat owner information.

**Response:** `200 OK`

### Delete Flat Owner

**DELETE** `/flat-owners/{id}`

Removes a flat owner from the system.

**Response:** `204 No Content`

---

## Family Member Management

### Create Family Member

**POST** `/family-members`

Adds a family member to a flat owner's account.

**Request Body:**
```json
{
  "firstName": "Mary",
  "lastName": "Doe",
  "relationship": "Spouse",
  "phoneNumber": "9876543212",
  "email": "mary.doe@example.com",
  "age": 35,
  "flatOwnerId": 1
}
```

**Response:** `201 Created`

### Get Family Members by Flat Owner

**GET** `/family-members/flat-owner/{flatOwnerId}`

Returns all family members for a specific flat owner.

**Response:** `200 OK`

### Update Family Member

**PUT** `/family-members/{id}`

Updates family member information.

**Response:** `200 OK`

### Delete Family Member

**DELETE** `/family-members/{id}`

Removes a family member.

**Response:** `204 No Content`

---

## Tenant Management

### Create Tenant

**POST** `/tenants`

Adds a tenant to a flat owner's property.

**Request Body:**
```json
{
  "firstName": "Bob",
  "lastName": "Johnson",
  "phoneNumber": "9876543214",
  "email": "bob.johnson@example.com",
  "leaseStartDate": "2024-10-01",
  "leaseEndDate": "2025-10-01",
  "rentAmount": 15000.0,
  "idProofType": "Passport",
  "idProofNumber": "AB1234567",
  "flatOwnerId": 1
}
```

**Response:** `201 Created`

### Get Tenants by Flat Owner

**GET** `/tenants/flat-owner/{flatOwnerId}`

Returns all tenants for a specific flat owner.

**Response:** `200 OK`

### Update Tenant

**PUT** `/tenants/{id}`

Updates tenant information.

**Response:** `200 OK`

### Delete Tenant

**DELETE** `/tenants/{id}`

Removes a tenant.

**Response:** `204 No Content`

---

## Maintenance Management

### Create Maintenance Payment

**POST** `/maintenance`

Records a maintenance payment and generates a receipt.

**Request Body:**
```json
{
  "flatOwnerId": 1,
  "amount": 5000.0,
  "paymentDate": "2025-10-15",
  "paymentMode": "Online",
  "transactionId": "TXN123456",
  "status": "COMPLETED",
  "remarks": "Monthly maintenance payment"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "flatOwnerId": 1,
  "flatNumber": "101",
  "amount": 5000.0,
  "paymentDate": "2025-10-15",
  "paymentMode": "Online",
  "transactionId": "TXN123456",
  "receiptNumber": "REC-2025-ABC12345",
  "status": "COMPLETED",
  "remarks": "Monthly maintenance payment"
}
```

### Get Maintenance Records by Flat Owner

**GET** `/maintenance/flat-owner/{flatOwnerId}`

Returns all maintenance payment records for a flat owner.

**Response:** `200 OK`

### Update Maintenance Record

**PUT** `/maintenance/{id}`

Updates a maintenance record.

**Response:** `200 OK`

---

## Visitor Management

### Create Visitor Entry

**POST** `/visitors`

Records a new visitor entry.

**Request Body:**
```json
{
  "name": "Alice Brown",
  "phoneNumber": "9876543215",
  "inTime": "2025-10-15T14:30:00",
  "flatNumber": "101",
  "flatOwnerId": 1,
  "reason": "Social visit"
}
```

**Response:** `201 Created`

### Get Pending Visitors for Flat Owner

**GET** `/visitors/flat-owner/{flatOwnerId}/pending`

Returns all visitors pending approval for a specific flat owner.

**Response:** `200 OK`

### Approve Visitor

**POST** `/visitors/{id}/approve`

Approves a visitor entry.

**Request Body:**
```json
{
  "remarks": "Family friend"
}
```

**Response:** `200 OK`

### Reject Visitor

**POST** `/visitors/{id}/reject`

Rejects a visitor entry.

**Request Body:**
```json
{
  "remarks": "Unknown person"
}
```

**Response:** `200 OK`

### Set Visitor Out Time

**POST** `/visitors/{id}/out`

Records when a visitor leaves.

**Request Body:**
```json
{
  "outTime": "2025-10-15T16:30:00"
}
```

**Response:** `200 OK`

---

## Error Responses

All endpoints may return the following error responses:

**400 Bad Request**
```json
{
  "error": "Validation failed",
  "fieldName": "Error message"
}
```

**404 Not Found**
```json
{
  "error": "Resource not found"
}
```

**500 Internal Server Error**
```json
{
  "error": "Internal server error message"
}
```

---

## Testing the API

### Using cURL

```bash
# Get all flat owners
curl http://localhost:8080/api/flat-owners

# Create a flat owner
curl -X POST http://localhost:8080/api/flat-owners \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test.user",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User",
    "email": "test@example.com",
    "phoneNumber": "1234567890",
    "flatNumber": "201",
    "wing": "B",
    "floor": 2,
    "maintenanceAmount": 6000.0
  }'
```

### Sample Data

The application automatically initializes with sample data including:
- 2 Flat Owners
- 2 Family Members
- 1 Tenant
- 2 Maintenance Records
- 2 Visitor Records

You can view this data by accessing the respective GET endpoints.
