# 5. Database Schema & Entity Architecture

[← Previous: Package Structure & Organization](04-module-structure.md) | [Index](index.md) | [Next: API-to-Database-to-Entity Mappings →](06-api-database-entity-mappings.md)

---

### Database Schema Overview

The persistence layer interacts with a SQLite database containing 6 tables. Below is a detailed overview of the schema,
including columns, types, and constraints.

#### Core Entities

| Table       | Column          | Type      | Constraints               | Purpose                                             |
|:------------|:----------------|:----------|:--------------------------|:----------------------------------------------------|
| `character` | `id`            | `INTEGER` | `PRIMARY KEY`, `NOT NULL` | Unique identifier for the character.                |
|             | `name`          | `TEXT`    | `NOT NULL`                | Name of the character.                              |
|             | `status`        | `TEXT`    | `NOT NULL`                | Status of the character (Alive, Dead, Unknown).     |
|             | `species`       | `TEXT`    | `NOT NULL`                | Species of the character.                           |
|             | `type`          | `TEXT`    | `NULL`                    | Type or subspecies of the character.                |
|             | `gender`        | `TEXT`    | `NOT NULL`                | Gender of the character.                            |
|             | `origin_name`   | `TEXT`    | `NULL`                    | Name of the character's origin location.            |
|             | `origin_url`    | `TEXT`    | `NULL`                    | URL of the character's origin location.             |
|             | `location_name` | `TEXT`    | `NULL`                    | Name of the character's last known location.        |
|             | `location_url`  | `TEXT`    | `NULL`                    | URL of the character's last known location.         |
|             | `image`         | `TEXT`    | `NOT NULL`, `UNIQUE`      | URL to the character's image.                       |
|             | `episode`       | `TEXT`    | `NOT NULL`                | JSON array of episode URLs (raw).                   |
|             | `url`           | `TEXT`    | `NOT NULL`, `UNIQUE`      | URL to the character's own endpoint.                |
|             | `created`       | `TEXT`    | `NOT NULL`                | Time at which the character was created in the API. |
|             | `origin_id_`    | `INTEGER` | `NULL`, `FK(location.id)` | Internal reference to the origin location.          |
|             | `location_id_`  | `INTEGER` | `NULL`, `FK(location.id)` | Internal reference to the current location.         |
| `episode`   | `id`            | `INTEGER` | `PRIMARY KEY`, `NOT NULL` | Unique identifier for the episode.                  |
|             | `name`          | `TEXT`    | `NOT NULL`                | Name of the episode.                                |
|             | `air_date`      | `TEXT`    | `NOT NULL`                | Air date of the episode (human-readable).           |
|             | `episode`       | `TEXT`    | `NOT NULL`, `UNIQUE`      | Episode code (e.g., "S01E01").                      |
|             | `characters`    | `TEXT`    | `NOT NULL`                | JSON array of character URLs (raw).                 |
|             | `url`           | `TEXT`    | `NOT NULL`, `UNIQUE`      | URL to the episode's own endpoint.                  |
|             | `created`       | `TEXT`    | `NOT NULL`                | Time at which the episode was created in the API.   |
|             | `air_date_iso_` | `TEXT`    | `NOT NULL`                | ISO-8601 formatted air date for internal use.       |
| `location`  | `id`            | `INTEGER` | `PRIMARY KEY`, `NOT NULL` | Unique identifier for the location.                 |
|             | `name`          | `TEXT`    | `NOT NULL`                | Name of the location.                               |
|             | `type`          | `TEXT`    | `NULL`                    | Type of the location.                               |
|             | `dimension`     | `TEXT`    | `NULL`                    | Dimension in which the location is located.         |
|             | `residents`     | `TEXT`    | `NULL`                    | JSON array of character URLs (raw).                 |
|             | `url`           | `TEXT`    | `NOT NULL`, `UNIQUE`      | URL to the location's own endpoint.                 |
|             | `created`       | `TEXT`    | `NOT NULL`                | Time at which the location was created in the API.  |

#### Join Tables (Relationships)

| Table               | Column         | Type      | Constraints              | Relationship                                 |
|:--------------------|:---------------|:----------|:-------------------------|:---------------------------------------------|
| `character_episode` | `character_id` | `INTEGER` | `PK`, `FK(character.id)` | Many-to-Many: Character ↔ Episode            |
|                     | `episode_id`   | `INTEGER` | `PK`, `FK(episode.id)`   |                                              |
| `episode_character` | `episode_id`   | `INTEGER` | `PK`, `FK(episode.id)`   | Many-to-Many: Episode ↔ Character (Mirror)   |
|                     | `character_id` | `INTEGER` | `PK`, `FK(character.id)` |                                              |
| `location_resident` | `location_id`  | `INTEGER` | `PK`, `FK(location.id)`  | One-to-Many: Location ↔ Resident (Character) |
|                     | `resident_id`  | `INTEGER` | `PK`, `FK(character.id)` |                                              |

### Detailed Entity Analysis

All entities have been verified for:

1. **Table/Column Mappings**: Name matches between `@Table`/`@Column` and DB.
2. **Nullability Consistency**: 100% match between DB `NOT NULL` and JPA `@Basic(optional=false)`/
   `@Column(nullable=false)`.

#### Nullability Summary: ✅ **100% MATCH**

| Entity      | Fields Checked | Matches | Status     |
|-------------|----------------|---------|------------|
| Character   | 16             | 16      | ✅ PERFECT  |
| Episode     | 8              | 8       | ✅ PERFECT  |
| Location    | 7              | 7       | ✅ PERFECT  |
| Join Tables | 6              | 6       | ✅ PERFECT  |
| Embeddables | 2              | 2       | ✅ PERFECT  |
| **TOTAL**   | **39**         | **39**  | ✅ **100%** |

### Relationships, Foreign Keys & Indexes

* **Foreign Keys**: 8 database foreign keys exist and are correctly mapped:
    * `character.origin_id_` → `location.id`
    * `character.location_id_` → `location.id`
    * `location_resident.location_id` → `location.id`
    * `location_resident.resident_id` → `character.id`
    * `character_episode.character_id` → `character.id`
    * `character_episode.episode_id` → `episode.id`
    * `episode_character.episode_id` → `episode.id`
    * `episode_character.character_id` → `character.id`
* **Indexes**: 15 database indexes exist for performance optimization:
    * `location` (3): `type`, `dimension`, `created`
    * `character` (7): `status`, `species`, `type`, `gender`, `created`, `origin_id_`, `location_id_`
    * `episode` (2): `created`, `air_date_iso_`
    * Join Tables (3): `location_resident(resident_id)`, `character_episode(episode_id)`,
      `episode_character(character_id)`
* **Redundancy**: `episode_character` is a functional duplicate of `character_episode`, providing bidirectional mapping
  at the database level. Both are mapped to their respective JPA entities.

### Converters

The module uses a sophisticated converter architecture with both auto-applying and explicit converters:

#### Auto-Applying Converters (8 total)

These converters are automatically applied to matching types:

* `Character_StatusConverter` - Converts Character_Status enum ↔ String
* `Character_SpeciesConverter` - Converts Character_Species enum ↔ String
* `Character_TypeConverter` - Converts Character_Type enum ↔ String
* `Character_GenderConverter` - Converts Character_Gender enum ↔ String
* `Location_TypeConverter` - Converts Location_Type enum ↔ String
* `Location_DimensionConverter` - Converts Location_Dimension enum ↔ String

#### Explicit Converters (11 total)

These converters must be explicitly specified via `@Convert` annotation:

* `InstantStringConverter` - Converts `Instant` ↔ `String` (ISO-8601 format)
* `LocalDateStringConverter` - Converts `LocalDate` ↔ `String`
* `UrlStringConverter` - Converts `URL` ↔ `String` (via URI intermediate)
* `UrlListStringConverter` - Converts `List<URL>` ↔ `String` (comma-delimited)
* `UriStringConverter` - Base converter for URI ↔ String
* `UriListStringConverter` - Base converter for List<URI> ↔ String
* `Episode_AirDateConverter` - Custom converter for Episode air_date field

#### Converter Architecture

The converter system follows a layered architecture:

1. **Base Layer**: `__BaseConverter<X, Y>` - Generic converter with formatter/parser pattern
2. **String Layer**: `_StringConverter<T>` - Specialized for String conversions
3. **List Layer**: `__ListConverter<T>` and `_ListStringConverter<T>` - For collection conversions
4. **Enum Layer**: `_StringColumnEnumAttributeConverter<T>` - For enum-to-string conversions

### Named Queries

The module includes 6 optimized JPQL queries:

#### Character Queries (2)

* `Character.selectList_NameEqual_` - Find characters by exact name match
* `Character.selectList__OrderByIdAsc` - List all characters ordered by ID

#### Episode Queries (4)

* `Episode.SelectList__OrderByAirDateIso_Asc` - Episodes ordered by air date (ISO)
* `Episode.SelectList__OrderByEpisodeAsc` - Episodes ordered by episode code
* `Episode.SelectList__OrderByIdAsc` - Episodes ordered by ID
* `Episode.SelectSingle_WhereEpisodeEqual_` - Find episode by episode code (e.g., "S01E01")

---

## Entity Relationship Diagram

### Visual Representation

```
┌─────────────┐         ┌──────────────────┐         ┌─────────────┐
│  Character  │◄───────►│ CharacterEpisode │◄───────►│   Episode   │
│             │   N:M   │                  │   N:M   │             │
└─────────────┘         └──────────────────┘         └─────────────┘
      │                                                      │
      │                                                      │
      │                                                      │
      │ 1:N (origin)                                         │
      │ 1:N (location)                                       │
      │                                                      │
      ▼                                                      │
┌─────────────┐                                              │
│  Location   │                                              │
│             │                                              │
└─────────────┘                                              │
      │                                                      │
      │ 1:N (residents)                                      │
      │                                                      │
      ▼                                                      │
┌──────────────────┐                                         │
│ LocationResident │                                         │
└──────────────────┘                                         │
      │                                                      │
      │ N:1                                                  │
      │                                                      │
      ▼                                                      │
┌─────────────┐                                              │
│  Character  │◄─────────────────────────────────────────────┘
└─────────────┘
```

### Textual Representation

```
Character (1) ──< (N) CharacterEpisode (N) >── (1) Episode
Character (1) ──< (N) EpisodeCharacter (N) >── (1) Episode  [Deprecated]
Location (1) ──< (N) Character [as origin]
Location (1) ──< (N) Character [as location]
Location (1) ──< (N) LocationResident (N) >── (1) Character [as resident]
```

### Relationship Details

#### 1. Character ↔ Episode (Many-to-Many)

**Relationship Type**: Many-to-Many (bidirectional)

**Implementation**:
- **Join Table**: `character_episode`
- **Entity**: `CharacterEpisode`
- **Composite Key**: `CharacterEpisodeId` (character_id, episode_id)

**Cardinality**:
- One Character can appear in many Episodes
- One Episode can feature many Characters

**JPA Mapping**:
```java
// In Character.java
@ManyToMany
@JoinTable(name = CharacterEpisode.TABLE_NAME, ...)
private List<Episode> episodes_;

// In Episode.java
@ManyToMany(mappedBy = "episodes_")
private List<Character> characters_;
```

**Alternative Mapping** (Deprecated):
- **Join Table**: `episode_character`
- **Entity**: `EpisodeCharacter` (deprecated, use `CharacterEpisode` instead)

#### 2. Character → Location (Many-to-One)

**Relationship Type**: Many-to-One (unidirectional from Character to Location)

**Implementation**:
- **Foreign Keys**: `origin_id_`, `location_id_` in `character` table
- **JPA Mapping**: `@ManyToOne` with `@JoinColumn`

**Cardinality**:
- Many Characters can have the same origin Location
- Many Characters can be at the same current Location
- One Character has one origin Location (nullable)
- One Character has one current Location (nullable)

**JPA Mapping**:
```java
// In Character.java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = COLUMN_NAME_ORIGIN_ID_, insertable = false, updatable = false)
private Location origin_;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = COLUMN_NAME_LOCATION_ID_, insertable = false, updatable = false)
private Location location_;
```

#### 3. Location → Character (One-to-Many)

**Relationship Type**: One-to-Many (bidirectional, inverse side)

**Implementation**:
- **Join Table**: `location_resident`
- **Entity**: `LocationResident`
- **Composite Key**: `LocationResidentId` (location_id, resident_id)

**Cardinality**:
- One Location can have many resident Characters
- One Character can be a resident of many Locations

**JPA Mapping**:
```java
// In Location.java
@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
private List<LocationResident> residents_;

// Inverse relationships (via Character)
@OneToMany(mappedBy = "origin_", fetch = FetchType.LAZY)
private List<Character> originCharacters_;

@OneToMany(mappedBy = "location_", fetch = FetchType.LAZY)
private List<Character> locationCharacters_;
```

### Relationship Patterns

#### Pattern 1: Explicit Join Table Entities
**Used For**: Many-to-Many relationships
**Benefits**: Full control, can add additional columns, explicit relationship management
**Examples**: `CharacterEpisode`, `LocationResident`

#### Pattern 2: Foreign Key Relationships
**Used For**: Many-to-One relationships
**Benefits**: Simple, database-level referential integrity, efficient queries
**Examples**: `Character.origin_` → `Location`, `Character.location_` → `Location`

#### Pattern 3: Inverse Relationships
**Used For**: Bidirectional One-to-Many
**Benefits**: Navigation from both sides, consistent relationship management
**Examples**: `Location.originCharacters_` ← `Character.origin_`

### Relationship Constraints

| Constraint                       | From             | To           | Action       |
|----------------------------------|------------------|--------------|--------------|
| `character.origin_id_`           | Character        | Location.id  | NULL allowed |
| `character.location_id_`         | Character        | Location.id  | NULL allowed |
| `character_episode.character_id` | CharacterEpisode | Character.id | CASCADE      |
| `character_episode.episode_id`   | CharacterEpisode | Episode.id   | CASCADE      |
| `location_resident.location_id`  | LocationResident | Location.id  | CASCADE      |
| `location_resident.resident_id`  | LocationResident | Character.id | CASCADE      |

### Query Patterns

**Character → Episodes**:
```java
Character character = em.find(Character.class, 1);
List<Episode> episodes = character.getEpisodes_();
```

**Episode → Characters**:
```java
Episode episode = em.find(Episode.class, 1);
List<Character> characters = episode.getCharacters_();
```

**Location → Residents**:
```java
Location location = em.find(Location.class, 1);
List<LocationResident> residents = location.getResidents_();
```

### Performance Considerations

- **Lazy Loading**: All relationships use `FetchType.LAZY` to prevent over-fetching
- **Indexes**: Database indexes on foreign key columns for fast lookups
- **Query Optimization**: Use `JOIN FETCH` for eager loading when needed

### Deprecated Relationships

**EpisodeCharacter** (Deprecated):
- **Status**: ⚠️ Deprecated
- **Reason**: Functional duplicate of `CharacterEpisode`
- **Migration Path**: Use `CharacterEpisode` and `CharacterEpisodeId` instead

---

[← Previous: Package Structure & Organization](04-module-structure.md) | [Index](index.md) | [Next: API-to-Database-to-Entity Mappings →](06-api-database-entity-mappings.md)
