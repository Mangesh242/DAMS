# Society Management System - Backend

This is the backend API for the Society Management System built with Spring Boot.

## Features

- Flat Owner Management
- Family Member Management
- Tenant Management
- Maintenance Payment and Receipt Generation
- Visitor Management with Approval Workflow

## Technologies

- Spring Boot 3.1.5
- Spring Data JPA
- Spring Security
- H2 Database
- Maven

## Running the Application

```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Flat Owners
- GET /api/flat-owners - Get all flat owners
- GET /api/flat-owners/{id} - Get flat owner by ID
- POST /api/flat-owners - Create new flat owner
- PUT /api/flat-owners/{id} - Update flat owner
- DELETE /api/flat-owners/{id} - Delete flat owner

### Family Members
- GET /api/family-members - Get all family members
- GET /api/family-members/{id} - Get family member by ID
- GET /api/family-members/flat-owner/{flatOwnerId} - Get family members by flat owner
- POST /api/family-members - Create new family member
- PUT /api/family-members/{id} - Update family member
- DELETE /api/family-members/{id} - Delete family member

### Tenants
- GET /api/tenants - Get all tenants
- GET /api/tenants/{id} - Get tenant by ID
- GET /api/tenants/flat-owner/{flatOwnerId} - Get tenants by flat owner
- POST /api/tenants - Create new tenant
- PUT /api/tenants/{id} - Update tenant
- DELETE /api/tenants/{id} - Delete tenant

### Maintenance
- GET /api/maintenance - Get all maintenance records
- GET /api/maintenance/{id} - Get maintenance record by ID
- GET /api/maintenance/flat-owner/{flatOwnerId} - Get maintenance records by flat owner
- POST /api/maintenance - Create new maintenance record
- PUT /api/maintenance/{id} - Update maintenance record
- DELETE /api/maintenance/{id} - Delete maintenance record

### Visitors
- GET /api/visitors - Get all visitors
- GET /api/visitors/{id} - Get visitor by ID
- GET /api/visitors/flat-owner/{flatOwnerId} - Get visitors by flat owner
- GET /api/visitors/flat-owner/{flatOwnerId}/pending - Get pending visitors by flat owner
- POST /api/visitors - Create new visitor
- PUT /api/visitors/{id} - Update visitor
- POST /api/visitors/{id}/approve - Approve visitor
- POST /api/visitors/{id}/reject - Reject visitor
- POST /api/visitors/{id}/out - Set visitor out time
- DELETE /api/visitors/{id} - Delete visitor

## Database

The application uses H2 in-memory database. You can access the H2 console at:
`http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:societydb`
- Username: `sa`
- Password: (leave empty)
