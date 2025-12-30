# 3. Technical Specifications

[← Previous: Context & Objectives](02-context-objectives.md) | [Index](index.md) | [Next: Persistence Layer Analysis →](04-persistence-layer-analysis.md)

---

## Technology Stack

### Core Platform

| Component               | Version | Purpose              |
|-------------------------|---------|----------------------|
| **Java**                | 25      | Programming language |
| **Jakarta EE**          | 11.0.0  | Enterprise platform  |
| **Jakarta Persistence** | 3.2     | JPA API              |
| **Maven**               | 3.6.3+  | Build tool           |

### ORM Providers

| Provider        | Version     | Status        | Notes                      |
|-----------------|-------------|---------------|----------------------------|
| **Hibernate**   | 7.2.0.Final | ✅ Default     | Recommended for production |
| **EclipseLink** | 5.0.0-B13   | ✅ Supported   | Via Maven profile          |
| **EclipseLink** | 5.0.0-B13   | [x] Supported | Via Maven profile          |

**Provider Selection**: The codebase depends strictly on **Jakarta Persistence 3.2** API for high portability. No
provider-specific code is used, ensuring compatibility across ORM providers.

### Database

| Component       | Version  | Purpose         |
|-----------------|----------|-----------------|
| **SQLite**      | 3.51.1.0 | Database engine |
| **SQLite JDBC** | 3.51.1.0 | JDBC driver     |

**Database Characteristics**:

- File-based database (no server required)
- ACID-compliant transactions
- Suitable for read-heavy workloads
- Excellent for development and testing

### Advanced Mapping

| Feature                  | Implementation           | Purpose                     |
|--------------------------|--------------------------|-----------------------------|
| **Attribute Converters** | Custom converters        | URL, Date, List conversions |
| **Enum Converters**      | Auto-applying converters | Type-safe enum handling     |
| **Embeddable Objects**   | `@Embedded`              | Complex value objects       |
| **Composite Keys**       | `@EmbeddedId`            | Join table primary keys     |

### Quality Assurance

| Tool                    | Version     | Purpose                        |
|-------------------------|-------------|--------------------------------|
| **JUnit**               | 6.0.1       | Unit testing framework         |
| **Mockito**             | 5.21.0      | Mocking framework              |
| **EqualsVerifier**      | 4.2.5       | equals/hashCode verification   |
| **AssertJ**             | 4.0.0-M1    | Fluent assertions              |
| **Hibernate Validator** | 9.1.0.Final | Bean Validation implementation |
| **JaCoCo**              | 0.8.14      | Code coverage                  |

### Build & Development Tools

| Tool                        | Version     | Purpose                      |
|-----------------------------|-------------|------------------------------|
| **Maven Compiler Plugin**   | 3.14.1      | Java 25 compilation          |
| **Maven Javadoc Plugin**    | 3.12.0      | API documentation            |
| **Maven Surefire Plugin**   | 3.5.4       | Unit test execution          |
| **Maven Failsafe Plugin**   | 3.5.4       | Integration test execution   |
| **Hibernate JPA Model Gen** | 7.2.0.Final | Metamodel generation         |
| **Lombok**                  | 1.18.42     | Code generation (test scope) |

---

## Codebase Statistics

### File Counts

| Category               | Count | Details                                                                                                  |
|------------------------|-------|----------------------------------------------------------------------------------------------------------|
| **Total Java Files**   | 47    | All source files                                                                                         |
| **Entity Classes**     | 6     | Main entities (Character, Episode, Location, CharacterEpisode, EpisodeCharacter, LocationResident)       |
| **Embeddable Classes** | 4     | Composite keys and value objects                                                                         |
| **Converter Classes**  | 19    | Including base classes and implementations                                                               |
| **Enum Types**         | 6     | Character_Status, Character_Species, Character_Type, Character_Gender, Location_Type, Location_Dimension |
| **Base Classes**       | 8     | Infrastructure classes (`_BaseEntity`, `__Base`, etc.)                                                   |
| **Utility Classes**    | 4     | Constants, utilities, enum helpers                                                                       |

### Converter Breakdown

| Type                         | Count | Examples                                           |
|------------------------------|-------|----------------------------------------------------|
| **Auto-Applying Converters** | 6     | Character_StatusConverter, Location_TypeConverter  |
| **Explicit Converters**      | 7     | InstantStringConverter, UrlStringConverter         |
| **Base Converter Classes**   | 6     | __BaseConverter, _StringConverter, __ListConverter |

### Query Statistics

| Type                     | Count | Distribution                                |
|--------------------------|-------|---------------------------------------------|
| **Named Queries (JPQL)** | 6     | Character (2), Episode (4)                  |
| **Query Types**          | 3     | Select list, Select single, Ordered queries |

### Database Schema

| Component              | Count | Details                                                                               |
|------------------------|-------|---------------------------------------------------------------------------------------|
| **Tables**             | 6     | character, episode, location, character_episode, episode_character, location_resident |
| **Foreign Keys**       | 8     | Relationship constraints                                                              |
| **Indexes**            | 15    | Performance optimization indexes                                                      |
| **Unique Constraints** | 5     | url, image, episode fields                                                            |

### Relationship Statistics

| Relationship Type | Count | Examples                                           |
|-------------------|-------|----------------------------------------------------|
| **Many-to-Many**  | 1     | Character ↔ Episode                                |
| **Many-to-One**   | 2     | Character → Location (origin, location)            |
| **One-to-Many**   | 3     | Location → Character (residents, origin, location) |

---

## Dependency Analysis

### Provided Dependencies (Jakarta EE)

These are provided by the Jakarta EE runtime:

- `jakarta.persistence-api` - JPA API
- `jakarta.validation-api` - Bean Validation API
- `jakarta.annotation-api` - Common annotations
- `jakarta.enterprise.cdi-api` - CDI API
- `jakarta.inject-api` - Dependency Injection API

### Test Dependencies

- `org.hibernate.orm:hibernate-core` - Hibernate ORM (test scope)
- `org.hibernate.orm:hibernate-community-dialects` - SQLite dialect (test scope)
- `org.hibernate.validator:hibernate-validator` - Bean Validation implementation (test scope)
- `org.xerial:sqlite-jdbc` - SQLite JDBC driver (test scope)
- `ch.qos.logback:logback-classic` - Logging (test scope)

### Build-Time Dependencies

- `org.hibernate.orm:hibernate-jpamodelgen` - Metamodel generation
- `org.projectlombok:lombok` - Code generation (test scope only)

---

## Compiler & Language Features

### Java 25 Features Used

- **Text Blocks**: Multi-line string literals for JPQL queries
- **Records**: Not used (traditional classes preferred for entities)
- **Pattern Matching**: Not used (traditional control flow)
- **Sealed Classes**: Not used (open inheritance hierarchy)

### Compiler Configuration

```xml
<maven.compiler.source>25</maven.compiler.source>
<maven.compiler.target>25</maven.compiler.target>
<maven.compiler.release>25</maven.compiler.release>
```

### Annotation Processing

- **Hibernate JPA Model Gen**: Generates `*_` metamodel classes
- **Lombok**: Used only in test scope for code generation

---

## Build Profiles

### Default Profile: `hibernate`

- Uses Hibernate 7.2.0.Final as the JPA provider
- Active by default
- Recommended for production use

### Alternative Profile: `eclipselink`

- Uses EclipseLink 5.0.0-B13 as the JPA provider
- Activated via `-Peclipselink` Maven flag
- Useful for testing provider compatibility

---

## Code Quality Tools

### Static Analysis

- **SonarQube**: Code quality and security analysis
- **Checkstyle**: Code style enforcement (via Maven plugin)
- **PMD**: Code quality analysis (via Maven plugin)

### Code Coverage

- **JaCoCo**: Code coverage reporting
- **Coverage Target**: Not explicitly defined (monitored via SonarCloud)

---

[← Previous: Context & Objectives](02-context-objectives.md) | [Index](index.md) | [Next: Persistence Layer Analysis →](04-persistence-layer-analysis.md)
