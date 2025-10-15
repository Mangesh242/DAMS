# System Architecture

## High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     Client Layer                             │
│  ┌────────────────┐                  ┌──────────────────┐   │
│  │ Angular        │                  │ External API     │   │
│  │ Frontend       │                  │ Clients          │   │
│  └───────┬────────┘                  └────────┬─────────┘   │
└──────────┼────────────────────────────────────┼─────────────┘
           │                                    │
           │ HTTP/REST                          │ HTTP/REST
           │                                    │
┌──────────▼────────────────────────────────────▼─────────────┐
│                  Spring Boot Backend                         │
│  ┌──────────────────────────────────────────────────────┐   │
│  │              REST Controllers Layer                   │   │
│  │  FlatOwner | FamilyMember | Tenant | Maintenance |   │   │
│  │                    Visitor Controllers               │   │
│  └─────────────────────┬────────────────────────────────┘   │
│                        │                                     │
│  ┌─────────────────────▼────────────────────────────────┐   │
│  │              Service Layer                            │   │
│  │  Business Logic | Validation | Receipt Generation    │   │
│  └─────────────────────┬────────────────────────────────┘   │
│                        │                                     │
│  ┌─────────────────────▼────────────────────────────────┐   │
│  │           Repository Layer (JPA)                      │   │
│  │  Data Access | Query Methods | Relationships          │   │
│  └─────────────────────┬────────────────────────────────┘   │
└────────────────────────┼────────────────────────────────────┘
                         │
           ┌─────────────▼─────────────┐
           │     H2/MySQL Database     │
           │   (Persistence Layer)     │
           └───────────────────────────┘
```

## Entity Relationship Diagram

```
┌──────────────┐
│    User      │
│  (Abstract)  │
├──────────────┤
│ id           │
│ username     │
│ password     │
│ firstName    │
│ lastName     │
│ email        │
│ phoneNumber  │
│ role         │
│ active       │
└──────┬───────┘
       │
       │ Inheritance
       │
       ├─────────────────────┬─────────────────┐
       │                     │                 │
┌──────▼───────┐      ┌──────▼──────┐   ┌─────▼─────┐
│  FlatOwner   │      │   Admin     │   │ Other...  │
├──────────────┤      ├─────────────┤   └───────────┘
│ flatNumber   │      │ department  │
│ wing         │      └─────────────┘
│ floor        │
│ maintenance  │
│ Amount       │
└──────┬───────┘
       │
       │ One-to-Many
       │
       ├────────────────┬────────────────┬──────────────┐
       │                │                │              │
┌──────▼─────────┐ ┌────▼────────┐ ┌────▼─────────┐ ┌─▼──────────┐
│ FamilyMember   │ │   Tenant    │ │ Maintenance  │ │  Visitor   │
├────────────────┤ ├─────────────┤ ├──────────────┤ ├────────────┤
│ firstName      │ │ firstName   │ │ amount       │ │ name       │
│ lastName       │ │ lastName    │ │ paymentDate  │ │ phoneNo    │
│ relationship   │ │ leaseStart  │ │ paymentMode  │ │ inTime     │
│ phoneNumber    │ │ leaseEnd    │ │ receiptNo    │ │ outTime    │
│ email          │ │ rentAmount  │ │ status       │ │ flatNumber │
│ age            │ │ idProofType │ │ txnId        │ │ reason     │
└────────────────┘ │ idProofNo   │ └──────────────┘ │ approval   │
                   │ active      │                  │ Status     │
                   └─────────────┘                  └────────────┘
```

## Component Flow

### 1. Create Flat Owner Flow
```
Client → POST /api/flat-owners
   ↓
FlatOwnerController.createFlatOwner()
   ↓
FlatOwnerService.createFlatOwner()
   ├─ Validate unique username
   ├─ Validate unique email
   ├─ Validate unique flat number
   ├─ Encrypt password
   └─ Save to database
   ↓
FlatOwnerRepository.save()
   ↓
Database (User + FlatOwner tables)
   ↓
Return FlatOwnerResponse
```

### 2. Maintenance Payment & Receipt Generation Flow
```
Client → POST /api/maintenance
   ↓
MaintenanceController.createMaintenance()
   ↓
MaintenanceService.createMaintenance()
   ├─ Validate flat owner exists
   ├─ Generate receipt number (REC-YYYY-XXXXXXXX)
   ├─ Set payment details
   └─ Save to database
   ↓
MaintenanceRepository.save()
   ↓
Database (Maintenance table)
   ↓
Return MaintenanceResponse with receiptNumber
```

### 3. Visitor Approval Workflow
```
Client → POST /api/visitors
   ↓
VisitorController.createVisitor()
   ↓
VisitorService.createVisitor()
   ├─ Validate flat owner exists
   ├─ Set approval status to PENDING
   └─ Save to database
   ↓
[Flat Owner Reviews]
   ↓
Client → POST /api/visitors/{id}/approve
   ↓
VisitorService.approveVisitor()
   ├─ Update approval status to APPROVED
   ├─ Set approval remarks
   ├─ Set approval date
   └─ Save to database
```

## API Layer Structure

```
/api/flat-owners
  ├─ POST    /               (Create)
  ├─ GET     /               (List all)
  ├─ GET     /{id}           (Get by ID)
  ├─ PUT     /{id}           (Update)
  └─ DELETE  /{id}           (Delete)

/api/family-members
  ├─ POST    /                        (Create)
  ├─ GET     /                        (List all)
  ├─ GET     /{id}                    (Get by ID)
  ├─ GET     /flat-owner/{flatOwnerId} (List by owner)
  ├─ PUT     /{id}                    (Update)
  └─ DELETE  /{id}                    (Delete)

/api/tenants
  ├─ POST    /                        (Create)
  ├─ GET     /                        (List all)
  ├─ GET     /{id}                    (Get by ID)
  ├─ GET     /flat-owner/{flatOwnerId} (List by owner)
  ├─ PUT     /{id}                    (Update)
  └─ DELETE  /{id}                    (Delete)

/api/maintenance
  ├─ POST    /                        (Create & generate receipt)
  ├─ GET     /                        (List all)
  ├─ GET     /{id}                    (Get by ID)
  ├─ GET     /flat-owner/{flatOwnerId} (List by owner)
  ├─ PUT     /{id}                    (Update)
  └─ DELETE  /{id}                    (Delete)

/api/visitors
  ├─ POST    /                        (Create)
  ├─ GET     /                        (List all)
  ├─ GET     /{id}                    (Get by ID)
  ├─ GET     /flat-owner/{flatOwnerId}         (List by owner)
  ├─ GET     /flat-owner/{flatOwnerId}/pending (Pending approvals)
  ├─ POST    /{id}/approve            (Approve)
  ├─ POST    /{id}/reject             (Reject)
  ├─ POST    /{id}/out                (Set out time)
  ├─ PUT     /{id}                    (Update)
  └─ DELETE  /{id}                    (Delete)
```

## Security Architecture

```
┌──────────────────────────────────────┐
│        HTTP Request                   │
└───────────────┬──────────────────────┘
                │
    ┌───────────▼───────────┐
    │  Security Filter      │
    │  Chain                │
    ├───────────────────────┤
    │ 1. CSRF Filter        │
    │ 2. Authentication     │
    │ 3. Authorization      │
    │ 4. Session Mgmt       │
    └───────────┬───────────┘
                │
    ┌───────────▼───────────┐
    │  REST Controller      │
    └───────────┬───────────┘
                │
    ┌───────────▼───────────┐
    │  Business Logic       │
    └───────────┬───────────┘
                │
    ┌───────────▼───────────┐
    │  Database             │
    └───────────────────────┘
```

## Validation Flow

```
Client Request
   ↓
@Valid Annotation Triggers
   ↓
Bean Validation
   ├─ @NotBlank
   ├─ @NotNull
   ├─ @Email
   ├─ @Pattern
   ├─ @Min / @Max
   └─ @Size
   ↓
[If Valid] → Proceed to Controller
[If Invalid] → GlobalExceptionHandler
   ↓
Return 400 Bad Request with field errors
```

## Data Flow for Receipt Generation

```
1. Client submits maintenance payment
   ↓
2. MaintenanceService receives request
   ↓
3. generateReceiptNumber() called
   ├─ Format: "REC-{YEAR}-{UUID}"
   ├─ Example: "REC-2025-ABC12345"
   └─ Ensures uniqueness
   ↓
4. Create Maintenance entity
   ├─ Set all payment details
   ├─ Set generated receipt number
   └─ Set status
   ↓
5. Save to database
   ↓
6. Return response with receipt number
```

## Technologies & Versions

```
Backend Stack:
├─ Spring Boot: 3.1.5
├─ Java: 17
├─ Spring Data JPA: 3.1.5
├─ Spring Security: 6.1.5
├─ H2 Database: 2.2.224
├─ Lombok: 1.18.30
├─ Hibernate: 6.2.13
├─ Jackson: 2.15.3
└─ JUnit: 5.9.3

Build Tools:
└─ Maven: 3.x

Frontend Stack (Structure):
├─ Angular: 16.x
├─ TypeScript: 5.1.x
├─ RxJS: 7.8.x
└─ npm: 9.x
```

## Deployment Architecture (Recommended)

```
┌─────────────────────────────────────────────┐
│            Load Balancer (Nginx)            │
└──────────────┬──────────────────────────────┘
               │
       ┌───────┴───────┐
       │               │
┌──────▼──────┐  ┌────▼────────┐
│ Spring Boot │  │ Spring Boot │
│ Instance 1  │  │ Instance 2  │
└──────┬──────┘  └────┬────────┘
       │              │
       └──────┬───────┘
              │
    ┌─────────▼──────────┐
    │  MySQL/PostgreSQL   │
    │   (Production DB)   │
    └────────────────────┘
```

## Development vs Production

| Aspect | Development | Production |
|--------|-------------|------------|
| Database | H2 (in-memory) | MySQL/PostgreSQL |
| Security | Permit all | JWT/OAuth2 |
| CORS | Allow * | Specific origins |
| Logging | Console | File/External |
| Port | 8080 | Behind proxy |
| SSL | Not required | Required (HTTPS) |

---

**Version**: 1.0.0  
**Last Updated**: October 15, 2025
