# 2. Context & Objectives

[← Previous: Executive Summary](01-executive-summary.md) | [Index](index.md) | [Next: Technical Specifications →](03-technical-specifications.md)

---

## Contextual Meaning

This persistence layer transforms relational data from
the [rickandmortyapi-db](https://github.com/jinahya/rickandmortyapi-db) into structured JPA entities, providing a
standardized way to interact with the Rick and Morty universe's characters, locations, and episodes.

### Data Flow

```
Rick and Morty API (JSON)
    ↓
rickandmortyapi-db (SQLite)
    ↓
rickandmortyapi-persistence (JPA Entities)
    ↓
Java Applications
```

### Use Cases

1. **Data Analysis Applications**: Query and analyze character, episode, and location data
2. **API Backends**: Provide type-safe data access for REST/GraphQL APIs
3. **Reporting Tools**: Generate reports on character relationships and episode statistics
4. **Integration Layers**: Bridge between database and business logic layers
5. **Testing**: Provide test fixtures and mock data for application testing

### Target Audience

- **Application Developers**: Building applications that consume Rick and Morty data
- **Data Engineers**: Integrating Rick and Morty data into larger systems
- **API Developers**: Creating RESTful or GraphQL APIs over the data
- **Test Engineers**: Using entities for test data and fixtures

---

## Objectives

### 1. API-to-Entity Mapping

**Goal**: Accurately map JSON responses from the Rick and Morty API to relational database columns and Java JPA
entities.

**Achievements**:

- ✅ Complete mapping of all API fields to database columns
- ✅ Proper handling of nested objects (origin, location) using `@Embedded`
- ✅ URL list handling with custom converters
- ✅ ISO-8601 date formatting for temporal fields

**Challenges Addressed**:

- Complex nested JSON structures → Embeddable value objects
- Array fields (episodes, characters, residents) → List converters
- URL strings → Type-safe `java.net.URL` with automatic conversion

### 2. Type Safety & Validation

**Goal**: Ensure data integrity through Java type safety, custom converters (e.g., for URLs and Dates), and Jakarta Bean
Validation constraints.

**Achievements**:

- ✅ Strong typing with enums for status, species, type, gender, dimension
- ✅ Type-safe URL handling with `java.net.URL`
- ✅ Temporal types (`Instant`, `LocalDate`) instead of strings
- ✅ Comprehensive Bean Validation (`@NotNull`, `@NotBlank`, `@Past`, `@Positive`)
- ✅ JPA-level constraints (`@Basic(optional = false)`, `@Column(nullable = false)`)
- ✅ JSpecify nullability annotations (`@Nullable` from `org.jspecify.annotations`) for compile-time nullability checking

**Validation Strategy**:

- **Runtime Validation**: Jakarta Bean Validation (JSR 303/380)
- **Database Constraints**: JPA-level nullability constraints
- **Type Safety**: Compile-time type checking with enums and strong types
- **Nullability Annotations**: JSpecify annotations (`@Nullable` from `org.jspecify.annotations`) for compile-time nullability checking

### 3. Relational Integrity

**Goal**: Define and maintain complex relationships (ManyToMany, OneToMany) between entities using JPA specifications.

**Achievements**:

- ✅ Many-to-Many: Character ↔ Episode (bidirectional via join tables)
- ✅ Many-to-One: Character → Location (origin and current location)
- ✅ One-to-Many: Location → Character (residents, origin characters, location characters)
- ✅ Explicit join table entities for full relationship control
- ✅ Lazy loading for performance optimization

**Relationship Patterns**:

- **Explicit Join Tables**: `CharacterEpisode`, `LocationResident` provide full control
- **Bidirectional Mapping**: Both `character_episode` and `episode_character` tables mapped
- **Lazy Loading**: All relationships use `FetchType.LAZY` to prevent over-fetching

---

## Merits for Developers

### Ready-to-Use Data Model

Developers can immediately start building applications without worrying about:

- ❌ Underlying SQL queries
- ❌ Manual mapping logic
- ❌ Type conversions
- ❌ Relationship management

**Example**: Instead of writing SQL, developers can use:

```java
Character character = entityManager.find(Character.class, 1);
List<Episode> episodes = character.getEpisodes_();
```

### Consistency

Provides a **single source of truth** for the data structure:

- ✅ Consistent field names across all entities
- ✅ Standardized relationship patterns
- ✅ Uniform validation rules
- ✅ Predictable converter behavior

### Extensibility

Built with standard JPA and Jakarta EE technologies:

- ✅ **ORM Provider Agnostic**: Works with Hibernate, EclipseLink, and others
- ✅ **Jakarta EE 11 Compatible**: Uses latest Jakarta standards
- ✅ **Java 25 Features**: Leverages modern Java capabilities
- ✅ **Easy Integration**: Standard Maven dependency

### Developer Experience

- **Type Safety**: Compile-time checking prevents runtime errors
- **IDE Support**: Full autocomplete and navigation support
- **Documentation**: Comprehensive Javadoc for all entities
- **Testing**: Well-tested with comprehensive test suite

### Performance Considerations

- **Lazy Loading**: Relationships loaded only when accessed
- **Indexed Queries**: Database indexes for frequently queried fields
- **Named Queries**: Pre-optimized JPQL queries
- **Read-Optimized**: Mappings optimized for read operations

---

## Integration Points

### Maven Dependency

```xml
<dependency>
    <groupId>io.github.jinahya</groupId>
    <artifactId>rickandmortyapi-persistence</artifactId>
    <version>0.1.3-SNAPSHOT</version>
</dependency>
```

### Persistence Unit Configuration

The module requires a JPA persistence unit configured with:

- SQLite JDBC driver
- Entity classes listed in `persistence.xml`
- Converter classes registered
- Validation mode set to `CALLBACK`

### ORM Provider Support

- ✅ **Hibernate 7.2.0.Final** (default, recommended)
- ✅ **EclipseLink 5.0.0-B13** (via profile)
- ✅ Other JPA 3.2 compliant providers

---

[← Previous: Executive Summary](01-executive-summary.md) | [Index](index.md) | [Next: Technical Specifications →](03-technical-specifications.md)
