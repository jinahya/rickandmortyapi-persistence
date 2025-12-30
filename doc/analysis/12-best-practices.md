# 12. Best Practices & Usage Guidelines

[← Previous: Performance Analysis](11-performance-analysis.md) | [Index](index.md) | [Next: Final Verdict →](13-final-verdict.md)

---

## Overview

This section provides best practices, usage guidelines, and recommendations for using the Rick and Morty API Persistence Layer effectively.

---

## Usage Best Practices

### 1. Entity Manager Usage

#### ✅ DO

```java
// Use try-with-resources for EntityManager
try (EntityManager em = emf.createEntityManager()) {
    Character character = em.find(Character.class, 1);
    // Use entity
}

// Use transactions properly
em.getTransaction().begin();
try {
    // Persist operations
    em.getTransaction().commit();
} catch (Exception e) {
    em.getTransaction().rollback();
    throw e;
}
```

#### ❌ DON'T

```java
// Don't keep EntityManager open unnecessarily
EntityManager em = emf.createEntityManager();
// ... long-running operations ...
em.close(); // Too late, connection held too long

// Don't forget transaction boundaries
em.persist(entity); // Missing transaction
```

### 2. Query Usage

#### ✅ DO

```java
// Use named queries
TypedQuery<Character> query = em.createNamedQuery(
    "Character.selectList_NameEqual_", Character.class);
query.setParameter("name", "Rick Sanchez");
List<Character> characters = query.getResultList();

// Use parameterized queries
TypedQuery<Episode> query = em.createQuery(
    "SELECT e FROM Episode e WHERE e.airDate = :airDate", Episode.class);
query.setParameter("airDate", LocalDate.now());
```

#### ❌ DON'T

```java
// Don't use string concatenation in queries
String query = "SELECT c FROM Character c WHERE c.name = '" + name + "'";
// SQL injection risk!

// Don't use dynamic queries when named queries exist
// Prefer named queries for better performance
```

### 3. Relationship Handling

#### ✅ DO

```java
// Access relationships when needed (lazy loading)
Character character = em.find(Character.class, 1);
List<Episode> episodes = character.getEpisodes_(); // Loaded on access

// Use fetch joins when you know you'll need related entities
TypedQuery<Character> query = em.createQuery(
    "SELECT c FROM Character c JOIN FETCH c.episodes_", Character.class);
```

#### ❌ DON'T

```java
// Don't access relationships outside transaction
Character character = em.find(Character.class, 1);
em.close();
List<Episode> episodes = character.getEpisodes_(); // LazyInitializationException!

// Don't eagerly fetch all relationships
// Use lazy loading and fetch joins selectively
```

### 4. Validation

#### ✅ DO

```java
// Rely on Bean Validation
@NotNull
@NotBlank
private String name;

// Validation happens automatically with CALLBACK mode
```

#### ❌ DON'T

```java
// Don't bypass validation
// Always use proper validation annotations
```

---

## Architecture Best Practices

### 1. Entity Design

#### ✅ DO

- Use `@Entity` for main entities
- Use `@Embeddable` for value objects
- Use `@EmbeddedId` for composite keys
- Extend `_BaseEntity` for consistency

#### ❌ DON'T

- Don't mix entity and DTO concerns
- Don't expose internal implementation details
- Don't use mutable collections without proper synchronization

### 2. Converter Usage

#### ✅ DO

- Use auto-applying converters for enums
- Use explicit converters for complex types (URL, Instant, List)
- Test converters thoroughly

#### ❌ DON'T

- Don't create converters without testing
- Don't use converters for simple type conversions
- Don't forget null handling in converters

### 3. Naming Conventions

#### ✅ DO

- Follow existing naming patterns
- Use descriptive names
- Use constants for column names

#### ❌ DON'T

- Don't use abbreviations unnecessarily
- Don't deviate from established patterns

---

## Performance Best Practices

### 1. Query Optimization

#### ✅ DO

- Use named queries for frequently executed queries
- Use indexes on frequently queried columns
- Use lazy loading by default
- Use fetch joins when needed

#### ❌ DON'T

- Don't use N+1 query patterns
- Don't fetch unnecessary data
- Don't ignore query performance

### 2. Caching

#### ✅ DO

- Consider second-level cache for read-heavy scenarios
- Use query cache for repeated queries
- Implement application-level caching if needed

#### ❌ DON'T

- Don't cache without invalidation strategy
- Don't cache mutable data without versioning

### 3. Batch Operations

#### ✅ DO

- Use batch operations for bulk inserts/updates
- Use `flush()` and `clear()` for large batches
- Consider JDBC batch for very large operations

#### ❌ DON'T

- Don't load entire result sets into memory
- Don't perform operations one-by-one in loops

---

## Security Best Practices

### 1. Input Validation

#### ✅ DO

- Always validate input at the API layer
- Use Bean Validation annotations
- Sanitize user input

#### ❌ DON'T

- Don't trust user input
- Don't bypass validation
- Don't expose internal validation errors

### 2. SQL Injection Prevention

#### ✅ DO

- Always use parameterized queries
- Use named queries
- Validate input parameters

#### ❌ DON'T

- Never use string concatenation in queries
- Never execute raw SQL with user input
- Never trust external input

---

## Testing Best Practices

### 1. Unit Testing

#### ✅ DO

- Test all converters
- Test enum values
- Use EqualsVerifier for equals/hashCode
- Test edge cases

#### ❌ DON'T

- Don't skip testing
- Don't test implementation details
- Don't create brittle tests

### 2. Integration Testing

#### ✅ DO

- Use in-memory database for tests
- Test transaction boundaries
- Test relationships
- Clean up test data

#### ❌ DON'T

- Don't use production database for tests
- Don't leave test data in database
- Don't test external dependencies

---

## Migration Best Practices

### 1. Database Migrations

#### ✅ DO

- Use migration tools (Flyway, Liquibase)
- Version your migrations
- Test migrations on staging
- Backup before migration

#### ❌ DON'T

- Don't modify production schema directly
- Don't skip migration testing
- Don't forget rollback strategy

### 2. Code Migration

#### ✅ DO

- Migrate incrementally
- Test thoroughly
- Document breaking changes
- Provide migration guide

#### ❌ DON'T

- Don't make breaking changes without notice
- Don't remove deprecated code immediately
- Don't skip testing

---

## Common Pitfalls

### 1. LazyInitializationException

**Problem**: Accessing lazy-loaded relationships outside transaction

**Solution**: 
- Access relationships within transaction
- Use fetch joins
- Use `@Transactional` annotation

### 2. N+1 Query Problem

**Problem**: Loading related entities one-by-one

**Solution**:
- Use fetch joins
- Use `@BatchSize`
- Use `@EntityGraph`

### 3. Detached Entities

**Problem**: Modifying entities outside persistence context

**Solution**:
- Merge entities before modification
- Use DTOs for data transfer
- Re-attach entities to persistence context

---

## Conclusion

Following these best practices will help you use the Rick and Morty API Persistence Layer effectively, avoid common pitfalls, and build robust applications.

**Key Takeaways**:
- ✅ Use transactions properly
- ✅ Leverage lazy loading
- ✅ Use named queries
- ✅ Validate input
- ✅ Test thoroughly
- ✅ Monitor performance

---

[← Previous: Performance Analysis](12-performance-analysis.md) | [Index](index.md)

