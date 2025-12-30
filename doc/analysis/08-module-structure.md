# 8. Module Structure

[← Previous: Source Code Issues & Recommendations](07-source-code-issues-analysis.md) | [Index](index.md) | [Next: Final Verdict →](09-final-verdict.md)

---

## Package Organization

### Complete Package Tree

```
io.github.jinahya.rickandmortyapi.persistence
├── Character.java                               # Main character entity
├── Episode.java                                 # Main episode entity
├── Location.java                                # Main location entity
├── CharacterEpisode.java                        # Join table entity
├── EpisodeCharacter.java                        # Join table entity (deprecated)
├── LocationResident.java                        # Join table entity
├── CharacterEpisodeId.java                      # Composite key
├── EpisodeCharacterId.java                      # Composite key (deprecated)
├── LocationResidentId.java                      # Composite key
├── Character_NameAndUrl.java                    # Embeddable value object
├── Character_Status.java                        # Enum
├── Character_Species.java                       # Enum
├── Character_Type.java                          # Enum
├── Character_Gender.java                        # Enum
├── Character_StatusConverter.java               # Enum converter
├── Character_SpeciesConverter.java              # Enum converter
├── Character_TypeConverter.java                # Enum converter
├── Character_GenderConverter.java               # Enum converter
├── Location_Type.java                           # Enum
├── Location_Dimension.java                      # Enum
├── Location_TypeConverter.java                  # Enum converter
├── Location_DimensionConverter.java             # Enum converter
├── Episode_AirDateConverter.java                # Custom converter
├── _BaseEntity.java                             # Base entity class
├── __Base.java                                  # Root mapped superclass
├── _PersistenceConstants.java                   # Constants
├── _PersistenceUtils.java                       # Utilities (empty)
├── _StringColumnEnum.java                       # Enum interface
├── _StringColumnEnumAttributeConverter.java     # Enum converter base
├── _StringColumnEnumUtils.java                  # Enum utilities
├── __ColumnEnum.java                            # Column enum base
├── __ColumnEnumAttributeConverter.java          # Column enum converter base
├── __ColumnEnumUtils.java                       # Column enum utilities
└── converter/
    ├── __BaseConverter.java                     # Base converter
    ├── __ListConverter.java                     # List converter base
    ├── _StringConverter.java                    # String converter base
    ├── _ListStringConverter.java                # List string converter base
    ├── InstantStringConverter.java              # Instant converter
    ├── LocalDateStringConverter.java            # LocalDate converter
    ├── UrlStringConverter.java                  # URL converter
    ├── UrlListStringConverter.java              # URL list converter
    ├── UriStringConverter.java                  # URI converter
    ├── UriListStringConverter.java              # URI list converter
    ├── UrlStringConverter2.java                 # Alternative implementation (unused)
    └── UrlListStringConverter2.java             # Alternative implementation (unused)
```

---

## Package Structure Analysis

### Layer 1: Core Entities

**Purpose**: Main domain entities representing database tables

**Classes**:

- `Character.java` - Character entity
- `Episode.java` - Episode entity
- `Location.java` - Location entity

**Characteristics**:

- Extend `_BaseEntity<Integer>`
- Use `@Entity` and `@Table` annotations
- Include named queries
- Comprehensive validation

### Layer 2: Join Table Entities

**Purpose**: Explicit join table entities for relationship management

**Classes**:

- `CharacterEpisode.java` - Character ↔ Episode relationship
- `EpisodeCharacter.java` - Episode ↔ Character relationship (deprecated)
- `LocationResident.java` - Location ↔ Character relationship

**Characteristics**:

- Use `@EmbeddedId` for composite keys
- Explicit relationship control
- Lazy loading configured

### Layer 3: Composite Keys & Embeddables

**Purpose**: Composite primary keys and embeddable value objects

**Classes**:

- `CharacterEpisodeId.java` - Composite key for CharacterEpisode
- `EpisodeCharacterId.java` - Composite key for EpisodeCharacter (deprecated)
- `LocationResidentId.java` - Composite key for LocationResident
- `Character_NameAndUrl.java` - Embeddable for origin/location

**Characteristics**:

- Implement `Serializable`
- Override `equals()` and `hashCode()`
- Used with `@EmbeddedId` or `@Embedded`

### Layer 4: Enums

**Purpose**: Type-safe enumeration types

**Classes**:

- `Character_Status.java` - Character status enum
- `Character_Species.java` - Character species enum
- `Character_Type.java` - Character type enum
- `Character_Gender.java` - Character gender enum
- `Location_Type.java` - Location type enum
- `Location_Dimension.java` - Location dimension enum

**Characteristics**:

- Implement `_StringColumnEnum` interface
- String-based enums (stored as TEXT in database)
- Auto-applying converters

### Layer 5: Converters

**Purpose**: Attribute converters for type transformation

#### Enum Converters (Auto-Applying)

**Classes**:

- `Character_StatusConverter.java`
- `Character_SpeciesConverter.java`
- `Character_TypeConverter.java`
- `Character_GenderConverter.java`
- `Location_TypeConverter.java`
- `Location_DimensionConverter.java`

**Characteristics**:

- `@Converter(autoApply = true)`
- Extend `_StringColumnEnumAttributeConverter`
- Automatically applied to matching enum types

#### Explicit Converters

**Classes**:

- `Episode_AirDateConverter.java` - Custom air date conversion
- `InstantStringConverter.java` - Instant ↔ String (ISO-8601)
- `LocalDateStringConverter.java` - LocalDate ↔ String
- `UrlStringConverter.java` - URL ↔ String
- `UrlListStringConverter.java` - List<URL> ↔ String

**Characteristics**:

- Must be explicitly specified via `@Convert`
- Extend base converter classes
- Handle null values gracefully

### Layer 6: Base Classes

**Purpose**: Infrastructure and base classes

**Classes**:

- `__Base.java` - Root mapped superclass
- `_BaseEntity.java` - Base entity class
- `_PersistenceConstants.java` - Constants
- `_PersistenceUtils.java` - Utilities (currently empty)

**Characteristics**:

- Underscore prefixes indicate internal/package-private classes
- Provide common functionality
- Used by all entities

### Layer 7: Converter Infrastructure

**Purpose**: Base classes for converter system

**Classes**:

- `__BaseConverter.java` - Generic converter base
- `_StringConverter.java` - String conversion base
- `__ListConverter.java` - List conversion base
- `_ListStringConverter.java` - List string conversion base
- `_StringColumnEnumAttributeConverter.java` - Enum converter base
- `__ColumnEnumAttributeConverter.java` - Column enum converter base

**Characteristics**:

- Layered architecture
- Reusable patterns
- Formatter/parser pattern

### Layer 8: Enum Infrastructure

**Purpose**: Base classes and utilities for enum system

**Classes**:

- `_StringColumnEnum.java` - Enum interface
- `__ColumnEnum.java` - Column enum base
- `_StringColumnEnumUtils.java` - Enum utilities
- `__ColumnEnumUtils.java` - Column enum utilities

**Characteristics**:

- Interface-based design
- Utility methods for enum operations
- Consistent enum handling

---

## Dependency Graph

### Entity Dependencies

```
Character
├── _BaseEntity<Integer>
│   └── __Base
├── Character_NameAndUrl (embedded)
├── Character_Status (enum)
├── Character_Species (enum)
├── Character_Type (enum)
├── Character_Gender (enum)
├── Location (many-to-one)
└── Episode (many-to-many via CharacterEpisode)

Episode
├── _BaseEntity<Integer>
│   └── __Base
├── Character (many-to-many via CharacterEpisode)
└── Episode_AirDateConverter

Location
├── _BaseEntity<Integer>
│   └── __Base
├── Location_Type (enum)
├── Location_Dimension (enum)
├── Character (one-to-many via LocationResident)
└── Character (one-to-many via origin_/location_)
```

### Converter Dependencies

```
Enum Converters
├── _StringColumnEnumAttributeConverter
│   ├── __ColumnEnumAttributeConverter
│   │   └── AttributeConverter
│   └── _StringColumnEnum (interface)

Explicit Converters
├── InstantStringConverter
│   └── _StringConverter
│       └── __BaseConverter
├── UrlStringConverter
│   └── _StringConverter
│       └── __BaseConverter
└── UrlListStringConverter
    └── _ListStringConverter
        └── __ListConverter
            └── __BaseConverter
```

---

## Naming Conventions

### Class Naming

| Pattern      | Purpose                      | Examples                                          |
|--------------|------------------------------|---------------------------------------------------|
| `*Entity`    | Main entities                | `Character`, `Episode`, `Location`                |
| `*Id`        | Composite keys               | `CharacterEpisodeId`, `LocationResidentId`        |
| `*_*`        | Inner classes                | `Character_NameAndUrl`                            |
| `_*`         | Package-private base classes | `_BaseEntity`, `_StringConverter`                 |
| `__*`        | Root infrastructure          | `__Base`, `__BaseConverter`                       |
| `*Converter` | Attribute converters         | `Character_StatusConverter`, `UrlStringConverter` |

### Method Naming

| Pattern   | Purpose                 | Examples                   |
|-----------|-------------------------|----------------------------|
| `get*()`  | Getters                 | `getName()`, `getStatus()` |
| `set*()`  | Setters                 | `setName()`, `setStatus()` |
| `*_`      | Internal/package fields | `episodes_`, `characters_` |
| `doOn*()` | Lifecycle callbacks     | `doOnPostPersist()`        |

---

## Package Organization Principles

### 1. Separation of Concerns

- **Entities**: Domain model
- **Converters**: Type transformation (separate package)
- **Enums**: Type definitions
- **Base Classes**: Infrastructure

### 2. Layered Architecture

- **Layer 1**: Domain entities
- **Layer 2**: Relationship entities
- **Layer 3**: Value objects
- **Layer 4-8**: Infrastructure

### 3. Visibility Control

- **Public**: Entities, enums (API surface)
- **Package-private**: Base classes, utilities (internal)
- **Underscore prefix**: Internal classes (naming convention)

### 4. Consistency

- Consistent naming patterns
- Uniform structure across entities
- Standardized converter architecture
- Predictable organization

---

## Module Dependencies

### External Dependencies

| Dependency                | Scope    | Purpose            |
|---------------------------|----------|--------------------|
| `jakarta.persistence-api` | Provided | JPA API            |
| `jakarta.validation-api`  | Provided | Bean Validation    |
| `jakarta.annotation-api`  | Provided | Common annotations |
| `org.jspecify:jspecify`    | Compile  | Nullability annotations |

### Internal Dependencies

- All entities depend on base classes
- Converters depend on converter infrastructure
- Enums depend on enum infrastructure
- No circular dependencies

---

## Code Organization Metrics

| Metric                      | Value                                  |
|-----------------------------|----------------------------------------|
| **Total Classes**           | 47                                     |
| **Public Classes**          | ~25                                    |
| **Package-Private Classes** | ~22                                    |
| **Packages**                | 2 (main + converter)                   |
| **Max Depth**               | 4 levels (entity → base → base → base) |
| **Average Class Size**      | ~200-300 lines                         |
| **Largest Class**           | Character.java (~600+ lines)           |

---

## Extension Points

### Adding New Entities

1. Extend `_BaseEntity<ID>`
2. Add `@Entity` and `@Table` annotations
3. Define fields with proper mappings
4. Add validation constraints
5. Create test class extending `_BaseEntity_PersistenceTest`

### Adding New Converters

1. Extend appropriate base converter
2. Implement formatter/parser functions
3. Add `@Converter` annotation (with `autoApply = true` if needed)
4. Register in `persistence.xml` if explicit

### Adding New Enums

1. Implement `_StringColumnEnum` interface
2. Create converter extending `_StringColumnEnumAttributeConverter`
3. Add `@Converter(autoApply = true)` if auto-applying
4. Use in entity fields

---

[← Previous: Source Code Issues & Recommendations](07-source-code-issues-analysis.md) | [Index](index.md) | [Next: Final Verdict →](09-final-verdict.md)
