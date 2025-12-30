# 9. Entity Relationship Diagram Summary

[← Previous: Issues & Recommendations](08-issues-recommendations.md) | [Index](index.md) | [Next: Module Structure →](10-module-structure.md)

---

## Core Relationships

### Visual Representation

```
┌─────────────┐         ┌──────────────────┐         ┌─────────────┐
│  Character  │◄───────►│ CharacterEpisode │◄───────►│   Episode   │
│             │   N:M   │                  │   N:M   │             │
└─────────────┘         └──────────────────┘         └─────────────┘
       │                                                      │
       │                                                      │
       │                                                      │
       │ 1:N (origin)                                        │
       │ 1:N (location)                                      │
       │                                                      │
       ▼                                                      │
┌─────────────┐                                              │
│  Location   │                                              │
│             │                                              │
└─────────────┘                                              │
       │                                                      │
       │ 1:N (residents)                                    │
       │                                                      │
       ▼                                                      │
┌──────────────────┐                                         │
│ LocationResident │                                         │
└──────────────────┘                                         │
       │                                                      │
       │ N:1                                                 │
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

---

## Relationship Details

### 1. Character ↔ Episode (Many-to-Many)

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

**Use Cases**:

- Find all episodes featuring a specific character
- Find all characters in a specific episode
- Query character-episode relationships

---

### 2. Character → Location (Many-to-One)

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

**Use Cases**:

- Find all characters from a specific location
- Find all characters currently at a location
- Query character origin and current location

---

### 3. Location → Character (One-to-Many)

**Relationship Type**: One-to-Many (bidirectional, inverse side)

**Implementation**:

- **Join Table**: `location_resident`
- **Entity**: `LocationResident`
- **Composite Key**: `LocationResidentId` (location_id, resident_id)

**Cardinality**:

- One Location can have many resident Characters
- One Character can be a resident of many Locations (theoretically, though data suggests one primary location)

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

**Use Cases**:

- Find all residents of a location
- Find all characters whose origin is a location
- Find all characters currently at a location

---

## Relationship Patterns

### Pattern 1: Explicit Join Table Entities

**Used For**: Many-to-Many relationships

**Benefits**:

- Full control over join table
- Can add additional columns if needed
- Explicit relationship management
- Better for complex queries

**Examples**:

- `CharacterEpisode` - Character ↔ Episode
- `LocationResident` - Location ↔ Character (residents)

### Pattern 2: Foreign Key Relationships

**Used For**: Many-to-One relationships

**Benefits**:

- Simple and direct
- Database-level referential integrity
- Efficient queries
- Standard JPA pattern

**Examples**:

- `Character.origin_` → `Location`
- `Character.location_` → `Location`

### Pattern 3: Inverse Relationships

**Used For**: Bidirectional One-to-Many

**Benefits**:

- Navigation from both sides
- Consistent relationship management
- Lazy loading support

**Examples**:

- `Location.originCharacters_` ← `Character.origin_`
- `Location.locationCharacters_` ← `Character.location_`

---

## Relationship Constraints

### Foreign Key Constraints

| Constraint                       | From             | To           | Action       |
|----------------------------------|------------------|--------------|--------------|
| `character.origin_id_`           | Character        | Location.id  | NULL allowed |
| `character.location_id_`         | Character        | Location.id  | NULL allowed |
| `character_episode.character_id` | CharacterEpisode | Character.id | CASCADE      |
| `character_episode.episode_id`   | CharacterEpisode | Episode.id   | CASCADE      |
| `location_resident.location_id`  | LocationResident | Location.id  | CASCADE      |
| `location_resident.resident_id`  | LocationResident | Character.id | CASCADE      |

### Nullability

- **Origin/Location**: Both `origin_id_` and `location_id_` are nullable (characters may have unknown origin/location)
- **Join Tables**: All foreign keys in join tables are NOT NULL (required relationships)

---

## Query Patterns

### Finding Related Entities

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

**Location → Origin Characters**:

```java
Location location = em.find(Location.class, 1);

List<Character> originChars = location.getOriginCharacters_();
```

---

## Performance Considerations

### Lazy Loading

All relationships use `FetchType.LAZY` to prevent over-fetching:

- Relationships loaded only when accessed
- Prevents N+1 query problems
- Better performance for large datasets

### Indexes

Database indexes on foreign key columns:

- `character.origin_id_` - Indexed
- `character.location_id_` - Indexed
- `character_episode.episode_id` - Indexed
- `location_resident.resident_id` - Indexed

### Query Optimization

- Use `JOIN FETCH` for eager loading when needed
- Named queries for common relationship queries
- Batch fetching can be added if N+1 queries become an issue

---

## Deprecated Relationships

### EpisodeCharacter (Deprecated)

**Status**: ⚠️ Deprecated

**Reason**: Functional duplicate of `CharacterEpisode`

**Migration Path**: Use `CharacterEpisode` and `CharacterEpisodeId` instead

**Impact**: Still functional but should not be used in new code

---

[← Previous: Issues & Recommendations](08-issues-recommendations.md) | [Index](index.md) | [Next: Module Structure →](10-module-structure.md)
