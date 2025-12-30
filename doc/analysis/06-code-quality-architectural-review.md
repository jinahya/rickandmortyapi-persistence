# 6. Code Quality & Architectural Review

[← Previous: API-to-Database-to-Entity Mappings](05-api-database-entity-mappings.md) | [Index](index.md) | [Next: Source Code Issues Analysis →](07-source-code-issues-analysis.md)

---

### Code Quality

* **Naming Conventions**: Some internal classes use underscore prefixes (e.g., `_BaseEntity`). While non-standard, they
  are consistently used and suppressed where needed.
* **Documentation**: Thorough Javadoc for core entities; some converters and `Location.java` getters/setters still need
  completion.
* **Validation**: Extensive use of Bean Validation (JSR 303/380) ensures data integrity.

### Architectural Perspectives

#### Design Patterns

* **Base Entity Pattern**: All entities extend `_BaseEntity<ID>` which extends `__Base`, providing a consistent
  inheritance hierarchy
* **Embeddable Pattern**: Complex value objects (NameAndUrl) and composite keys (CharacterEpisodeId) use `@Embeddable`
* **Converter Pattern**: Layered converter architecture with base classes and specialized implementations
* **Enum Pattern**: String-based enums with auto-applying converters for type-safe database interactions

#### Data Access Strategy

* **Read-Only Optimization**: Many mappings use `insertable = false, updatable = false`, suggesting a database-first,
  read-centric approach. This is appropriate for API data that is typically loaded from external sources.
* **Lazy Loading**: `FetchType.LAZY` is used for all relationships to avoid over-fetching and improve performance
* **Join Table Entities**: Explicit join table entities (CharacterEpisode, LocationResident) provide full control over
  relationship management

#### Concurrency & Transactions

* **No Optimistic Locking**: No `@Version` field currently; optimistic locking should be considered if write operations
  are expanded
* **Transaction Type**: Uses `RESOURCE_LOCAL` transaction type (appropriate for standalone applications)

#### Type Safety

* **Enum-Based Types**: Status, Species, Type, Gender, and Dimension are strongly typed enums rather than raw strings
* **URL Types**: Uses `java.net.URL` instead of String for URL fields, with automatic conversion
* **Temporal Types**: Uses `java.time.Instant` and `java.time.LocalDate` instead of String for dates
* **Nullability Annotations**: Uses JSpecify annotations (`@Nullable` from `org.jspecify.annotations`) to mark nullable fields for compile-time nullability checking

#### Validation Strategy

* **Bean Validation**: Extensive use of Jakarta Bean Validation (`@NotNull`, `@NotBlank`, `@Past`, `@Positive`)
* **JPA-Level Validation**: `@Basic(optional = false)` and `@Column(nullable = false)` for database-level constraints
* **Nullability Annotations**: Uses JSpecify annotations (`@Nullable` from `org.jspecify.annotations`) for compile-time nullability checking on nullable fields
* **Validation Mode**: Configured as `CALLBACK` in persistence.xml for automatic validation

---

[← Previous: API-to-Database-to-Entity Mappings](05-api-database-entity-mappings.md) | [Index](index.md) | [Next: Source Code Issues Analysis →](07-source-code-issues-analysis.md)
