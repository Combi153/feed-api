# Feed API - Spring Boot + Kotlin + Exposed Learning Project

A social media feed API built with Spring Boot, Kotlin, and Exposed ORM for learning backend development concepts.

## 🎯 Project Overview

This is a toy project designed to learn and practice:

- **Spring Boot** framework fundamentals
- **Kotlin** programming language
- **Exposed** ORM for database operations
- RESTful API design
- Testing strategies
- Clean architecture principles

---

## 📋 Product Requirements Document (PRD)

### Core Features

#### Phase 1: Basic Feed Management ✅ (Current)

- [x] Create a feed post
- [x] Read feed posts (all and by ID)
- [x] Update a feed post
- [x] Delete a feed post
- [x] Basic domain model with validation
- [x] Exposed ORM integration

#### Phase 2: User Management 🚧

- [ ] User registration and authentication
- [ ] User profiles (username, bio, avatar URL)
- [ ] JWT-based authentication
- [ ] Associate feeds with users
- [ ] User CRUD operations

#### Phase 3: Social Features 📱

- [ ] Like/Unlike posts
- [ ] Comment on posts
- [ ] Follow/Unfollow users
- [ ] User feed timeline (posts from followed users)
- [ ] Post visibility (public/private/followers-only)

#### Phase 4: Advanced Features 🚀

- [ ] Image upload for posts
- [ ] Hashtag support
- [ ] Search functionality (users, posts, hashtags)
- [ ] Pagination and sorting
- [ ] Post analytics (view count, like count)
- [ ] Notifications system

#### Phase 5: Performance & Production 💪

- [ ] Caching (Redis)
- [ ] Rate limiting
- [ ] Database indexing optimization
- [ ] API documentation (Swagger/OpenAPI)
- [ ] Monitoring and logging
- [ ] Docker containerization

---

## 📚 Learning Missions (TODO List)

### Mission 1: Foundation Review ✅

- [x] Set up Spring Boot project with Kotlin
- [x] Configure Exposed ORM
- [x] Implement basic CRUD operations
- [x] Understand value classes (`@JvmInline`)
- [x] Learn data classes and domain modeling

### Mission 2: Testing Fundamentals 🧪

**Goal:** Learn test-driven development and testing strategies

- [ ] **Unit Tests**
    - [ ] Write tests for domain models (Feed validation logic)
    - [ ] Test service layer business logic
    - [ ] Mock dependencies with MockK

- [ ] **Integration Tests**
    - [ ] Test repository layer with H2 database
    - [ ] Test Exposed queries and transactions

- [ ] **API Tests**
    - [ ] Test REST endpoints with MockMvc
    - [ ] Test request validation
    - [ ] Test error responses and status codes

**Learning Topics:**

- JUnit 5 basics
- MockK for Kotlin
- Spring Boot Test annotations
- Test containers (optional)

---

### Mission 3: User Management & Authentication 🔐

**Goal:** Implement user system with security

- [ ] **User Domain**
    - [ ] Create User entity with Exposed
    - [ ] Implement password hashing (BCrypt)
    - [ ] Add email validation

- [ ] **Authentication**
    - [ ] Set up Spring Security
    - [ ] Implement JWT token generation
    - [ ] Create login/register endpoints
    - [ ] Add authentication filters

- [ ] **Authorization**
    - [ ] Protect endpoints with @PreAuthorize
    - [ ] Implement ownership checks (users can only edit their posts)
    - [ ] Add role-based access control (USER, ADMIN)

**Learning Topics:**

- Spring Security fundamentals
- JWT structure and claims
- Password encryption
- Security filters and context
- Kotlin extension functions for security

---

### Mission 4: Relationships & Advanced Queries 🔗

**Goal:** Master Exposed ORM features and database relationships

- [ ] **Database Relationships**
    - [ ] One-to-Many: User → Feeds
    - [ ] Many-to-Many: Users ↔ Followers
    - [ ] One-to-Many: Feed → Comments
    - [ ] Many-to-Many: Feeds ↔ Likes

- [ ] **Advanced Queries**
    - [ ] Join queries with Exposed DSL
    - [ ] Aggregate functions (COUNT, SUM)
    - [ ] Subqueries and complex filters
    - [ ] Batch operations

- [ ] **Transactions**
    - [ ] Learn transaction management
    - [ ] Implement @Transactional properly
    - [ ] Handle rollback scenarios

**Learning Topics:**

- Exposed DAO vs DSL
- Foreign keys and references
- Query optimization
- N+1 problem solutions
- Database migrations

---

### Mission 5: API Design & Validation 📝

**Goal:** Build robust and well-designed APIs

- [ ] **Request Validation**
    - [ ] Add Bean Validation annotations
    - [ ] Create custom validators
    - [ ] Implement @Valid and @Validated

- [ ] **Exception Handling**
    - [ ] Create custom exceptions
    - [ ] Implement @ControllerAdvice
    - [ ] Return proper error DTOs
    - [ ] Handle database constraint violations

- [ ] **Response Design**
    - [ ] Implement pagination (Page, Pageable)
    - [ ] Add sorting and filtering
    - [ ] Create consistent response wrappers
    - [ ] Use HATEOAS for resource links (optional)

**Learning Topics:**

- Jakarta Validation API
- Exception hierarchy
- Response Entity best practices
- DTOs vs Domain models
- API versioning strategies

---

### Mission 6: Social Features Implementation 👥

**Goal:** Build interactive social features

- [ ] **Likes System**
    - [ ] Create Like entity
    - [ ] Implement toggle like/unlike
    - [ ] Count likes efficiently
    - [ ] Prevent duplicate likes

- [ ] **Comments System**
    - [ ] Create Comment entity
    - [ ] Nested comments (optional)
    - [ ] Comment threading
    - [ ] Comment pagination

- [ ] **Follow System**
    - [ ] Create Follower/Following relationships
    - [ ] Implement follow/unfollow
    - [ ] Generate personalized feed
    - [ ] Follower recommendations (optional)

**Learning Topics:**

- Self-referential relationships
- Activity feed algorithms
- Denormalization strategies
- Timeline generation

---

### Mission 7: Search & Discovery 🔍

**Goal:** Implement search functionality

- [ ] **Basic Search**
    - [ ] Search posts by content
    - [ ] Search users by username
    - [ ] Use LIKE queries with Exposed

- [ ] **Hashtags**
    - [ ] Extract hashtags from content
    - [ ] Create Hashtag entity
    - [ ] Many-to-Many relationship
    - [ ] Trending hashtags

- [ ] **Full-Text Search** (Advanced)
    - [ ] Integrate Elasticsearch (optional)
    - [ ] Implement advanced search filters
    - [ ] Search ranking and relevance

**Learning Topics:**

- Text search patterns
- Regular expressions in Kotlin
- Database text search capabilities
- Search optimization

---

### Mission 8: Performance Optimization ⚡

**Goal:** Make the application production-ready

- [ ] **Caching**
    - [ ] Add Spring Cache abstraction
    - [ ] Integrate Redis
    - [ ] Cache frequently accessed data
    - [ ] Implement cache invalidation

- [ ] **Database Optimization**
    - [ ] Add database indexes
    - [ ] Analyze query performance
    - [ ] Implement connection pooling
    - [ ] Use database views (optional)

- [ ] **API Optimization**
    - [ ] Implement rate limiting (Bucket4j)
    - [ ] Add compression
    - [ ] Optimize JSON serialization
    - [ ] Use HTTP caching headers

**Learning Topics:**

- Cache strategies (LRU, TTL)
- Redis data structures
- Database explain plans
- API performance monitoring

---

### Mission 9: Documentation & DevOps 📦

**Goal:** Document and containerize the application

- [ ] **API Documentation**
    - [ ] Add Springdoc OpenAPI
    - [ ] Write endpoint descriptions
    - [ ] Document request/response models
    - [ ] Add example requests

- [ ] **Code Documentation**
    - [ ] Write KDoc comments
    - [ ] Generate API documentation
    - [ ] Create architecture diagrams

- [ ] **Containerization**
    - [ ] Create Dockerfile
    - [ ] Docker Compose setup
    - [ ] Multi-stage builds
    - [ ] Environment configuration

- [ ] **CI/CD** (Optional)
    - [ ] GitHub Actions workflow
    - [ ] Automated testing
    - [ ] Code coverage reports

**Learning Topics:**

- OpenAPI 3.0 specification
- Docker basics
- Docker networking
- Environment variables management

---

### Mission 10: Observability & Monitoring 📊

**Goal:** Add production-grade monitoring

- [ ] **Logging**
    - [ ] Structured logging (JSON)
    - [ ] Log levels and categories
    - [ ] Request/response logging
    - [ ] Error tracking

- [ ] **Metrics**
    - [ ] Spring Boot Actuator
    - [ ] Custom metrics
    - [ ] Prometheus integration (optional)

- [ ] **Health Checks**
    - [ ] Database health check
    - [ ] Custom health indicators
    - [ ] Readiness and liveness probes

**Learning Topics:**

- SLF4J and Logback
- Micrometer metrics
- Observability patterns
- 12-factor app principles

---

## 🏗️ Architecture

### Structure (Future)

```
src/main/kotlin/me/chanmin/feed_api/
├── domain/
│   ├── feed/            # Feed aggregate
│   ├── user/            # User aggregate
│   ├── comment/         # Comment aggregate
│   └── common/          # Shared domain logic
├── application/
│   ├── feed/            # Feed use cases
│   └── user/            # User use cases
├── infrastructure/
│   ├── persistence/     # Database implementation
│   ├── security/        # Security configuration
│   └── web/             # Controllers, DTOs
└── config/              # Application configuration
```

---

## 🛠️ Technology Stack

### Core

- **Kotlin** 1.9.25
- **Spring Boot** 2.7.4
- **Exposed** 0.61.0 (Kotlin SQL framework)
- **H2** Database (development)

### To Add

- **Spring Security** + JWT
- **Redis** (caching)
- **PostgreSQL** (production database)
- **MockK** (testing)
- **Springdoc OpenAPI** (documentation)
- **Docker** (containerization)

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or higher
- Gradle 7.x or higher

### Running the Application

```bash
./gradlew bootRun
```

### Running Tests

```bash
./gradlew test
```

### Building

```bash
./gradlew build
```

---

## 📝 API Endpoints

### Current Endpoints

#### Feeds

```
GET    /feeds           # Get all feeds
GET    /feeds/{id}      # Get feed by ID
POST   /feeds           # Create new feed
PATCH  /feeds/{id}      # Update feed
DELETE /feeds/{id}      # Delete feed
```

### Future Endpoints (Planned)

#### Users

```
POST   /auth/register   # Register new user
POST   /auth/login      # Login user
GET    /users/{id}      # Get user profile
PATCH  /users/{id}      # Update user profile
```

#### Social

```
POST   /feeds/{id}/like     # Like a post
DELETE /feeds/{id}/like     # Unlike a post
GET    /feeds/{id}/comments # Get comments
POST   /feeds/{id}/comments # Add comment
POST   /users/{id}/follow   # Follow user
DELETE /users/{id}/follow   # Unfollow user
GET    /timeline            # Get personalized feed
```

