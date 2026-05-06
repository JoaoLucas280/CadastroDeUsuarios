# AGENTS.md - AI Coding Agent Guide

## Project Overview
**CadastroDeUsuários** is a Spring Boot 4.0.5 REST API for user and vehicle registration management. It uses a layered architecture with two interconnected domains: Users and Cars, featuring a one-to-many relationship where each user can own multiple cars.

## Architecture Patterns

### Layered Service Architecture
```
Controller → Service → Repository → Entity (JPA)
```
Each domain (Usuário, Carros) follows this pattern:
- **Controller** (`*Controller.java`): REST endpoints with `@RestController` + `@RequestMapping`
- **Service** (`*Service.java`): Business logic with `@Transactional` annotations on all public methods
- **Repository** (`*Repository.java`): JPA repositories extending `JpaRepository<Entity, Long>`
- **Model** (`*Model.java`): JPA entities with Lombok annotations

**Critical Convention**: All service methods are wrapped with `@Transactional` - maintain this when adding new operations.

### Cross-Domain Relationship: User ↔ Car
- `UserModel` has `@OneToMany(mappedBy = "usuarios")` with `@JsonManagedReference`
- `CarModel` has `@ManyToOne` with `@JoinColumn(name = "carros_id")` and `@JsonBackReference`
- **Why**: JsonManagedReference/JsonBackReference prevent infinite JSON serialization loops during API responses

**Data Flow**: User → owns 0..* Cars. When retrieving a user via `/usuarios/{id}`, the response includes nested car objects.

## Key Technologies & Conventions

### Dependency Injection
Uses `@Autowired` field injection (not constructor injection). Example:
```java
@Autowired
private UserService userService;
```

### Lombok Annotations
All entity models use:
- `@Data` → generates getters, setters, toString(), equals(), hashCode()
- `@NoArgsConstructor` → default constructor for JPA
- `@AllArgsConstructor` → constructor with all fields

### Database Configuration
- H2 in-memory database at `jdbc:h2:mem:testdb`
- Hibernate DDL auto-update: `spring.jpa.hibernate.ddl-auto=update`
- SQL formatting enabled: `spring.jpa.properties.hibernate.format_sql=true`
- Console at `/h2-console`

### RESTful Endpoints Pattern
All controllers implement CRUD:
- `GET /endpoint` → list all
- `GET /endpoint/{id}` → get by ID
- `POST /endpoint` → create (request body)
- `PUT /endpoint/{id}` → update (sets ID from path, body contains data)
- `DELETE /endpoint/{id}` → delete by ID

**Portuguese Naming**: Service methods use Portuguese names (e.g., `listarTodos()`, `criarUsuario()`, `deletarPorId()`) - maintain this convention.

## Project Structure Reference

```
src/main/
├── java/com/cadastro/CadastroDeUsuarios/
│   ├── CadastroDeUsuariosApplication.java     [App entry point, @SpringBootApplication]
│   ├── Usuário/
│   │   ├── UserModel.java                      [Entity: tb_cadastro table]
│   │   ├── UserController.java                 [REST endpoints: /usuarios]
│   │   ├── UserService.java                    [Business logic, all @Transactional]
│   │   └── UserRepository.java                 [JpaRepository interface]
│   └── Carros/
│       ├── CarModel.java                       [Entity: tb_carros table]
│       ├── CarController.java                  [REST endpoints: /carros]
│       ├── CarService.java                     [Business logic, all @Transactional]
│       └── CarRepository.java                  [JpaRepository interface]
└── resources/
    └── application.properties                  [H2 config, Hibernate settings]
```

## Critical Development Workflows

### Building & Running
```powershell
# Build with tests
./mvnw clean package

# Run application
./mvnw spring-boot:run

# App starts on http://localhost:8080
# H2 Console: http://localhost:8080/h2-console
```

### Testing
Dependencies available: `spring-boot-starter-webmvc-test`, `spring-boot-starter-data-jpa-test`
Test file location: `src/test/java/com/cadastro/CadastroDeUsuarios/`

### Adding New Features
When adding endpoints:
1. Create/modify entity model in domain folder with `@Entity`, `@Table`, Lombok annotations
2. Create/extend repository interface (extends `JpaRepository<Model, Long>`)
3. Add business logic to service with `@Transactional` on all public methods
4. Add controller endpoints using REST annotations
5. Remember relationship annotations (`@OneToMany`, `@ManyToOne`) with Jackson circular reference handling

## Important Gotchas & Patterns

- **Exception Handling**: Service methods use `.get()` on Optional (no exception handling implemented) - this should be improved for production
- **No Validation**: Add `@Valid` and bean validation (jakarta.validation) if data validation is needed
- **H2 Memory DB**: Data is lost on application restart - fine for development, switch to persistent DB for production
- **Java Version**: Project targets Java 17 - use modern Java features when adding code

## Integration Points

- **Ports**: REST API on port 8080
- **Database Driver**: H2 in-memory JDBC driver
- **ORM Framework**: Hibernate (via Spring Data JPA)
- **Serialization**: Jackson (auto-configured by Spring Boot)

## File Naming & Package Structure

- **Entities**: `*Model.java` (e.g., `UserModel`, `CarModel`)
- **Controllers**: `*Controller.java` suffix with `@RequestMapping("/resourcename")`
- **Services**: `*Service.java` with `@Service` annotation
- **Repositories**: `*Repository.java` as interfaces
- **Package structure**: `com.cadastro.CadastroDeUsuarios.{DomainName}`

---
*Last Updated: May 2026 | Spring Boot 4.0.5 | Java 17*

