# Reverse Engineered Vibe Coding Guide

This document captures the high-level, conversational AI instructions and prompts that would be used to build this entire persistence module from scratch. These are the "vibe" instructions - natural, task-oriented prompts rather than detailed code specifications.

---

## Phase 1: Initial Setup & Discovery

### 1.1 Project Initialization
```
Create a Maven project for a JPA persistence module for the Rick and Morty API database.
```

### 1.2 Database Analysis
```
Analyze the rickandmortyapi.db SQLite database and extract the schema.
List all tables, columns, data types, and relationships.
```

### 1.3 Persistence Configuration
```
Set up persistence.xml with SQLite JDBC configuration.
Configure it to work with both Hibernate and EclipseLink.
```

---

## Phase 2: Base Infrastructure

### 2.1 Base Classes
```
Create base classes for entities:
- A mapped superclass for all entities
- A generic base entity class with ID type parameter
```

### 2.2 Converter Infrastructure
```
Create a base converter framework for attribute conversion:
- Base converter with formatter/parser pattern
- String converter base class
- List converter base class
```

### 2.3 Enum Infrastructure
```
Create infrastructure for string-based enums:
- Base interface for column enums
- Base converter for enum attributes
- Utility class for enum value lookup
```

---

## Phase 3: Core Converters

### 3.1 URL Converters
```
Create converters for URL types:
- URL to String converter (using URI as intermediate)
- List<URL> to String converter (comma-delimited)
```

### 3.2 Date/Time Converters
```
Create converters for temporal types:
- Instant to String converter (ISO-8601 format)
- LocalDate to String converter
```

### 3.3 List Converters
```
Create generic list converters:
- Base list converter for comma-delimited lists
- Specific implementations for URL lists
```

---

## Phase 4: Enum Types

### 4.1 Character Enums
```
Create enum types for Character entity:
- Status enum (Alive, Dead, unknown)
- Species enum (all species values)
- Type enum (all character types)
- Gender enum (Female, Male, Genderless, unknown)

For each enum:
- Implement the string column enum interface
- Create auto-applying converter
- Add valueOfColumnValue static method
```

### 4.2 Location Enums
```
Create enum types for Location entity:
- Type enum (all location types)
- Dimension enum (all dimension values)

For each enum:
- Implement the string column enum interface
- Create auto-applying converter
```

---

## Phase 5: Main Entities

### 5.1 Character Entity
```
Create Character entity class from the character table:
- Map all columns with proper types
- Use enum types for status, species, type, gender
- Use converters for URL, List<URL>, and Instant fields
- Add embedded NameAndUrl for origin and location
- Add ManyToOne relationships to Location
- Add ManyToMany relationship to Episode via CharacterEpisode
- Add validation annotations (@NotNull, @NotBlank, @Past, @Positive)
- Add named queries for common lookups
```

### 5.2 Episode Entity
```
Create Episode entity class from the episode table:
- Map all columns with proper types
- Use converters for URL, List<URL>, and Instant fields
- Add custom converter for air_date (LocalDate)
- Add computed field airDateIso_ for ISO date format
- Add ManyToMany relationship to Character via EpisodeCharacter
- Add lifecycle callbacks for computed fields
- Add validation annotations
- Add named queries for ordering and lookups
```

### 5.3 Location Entity
```
Create Location entity class from the location table:
- Map all columns with proper types
- Use enum types for type and dimension
- Use converters for URL, List<URL>, and Instant fields
- Add OneToMany relationship to Character (as origin)
- Add OneToMany relationship to Character (as location)
- Add OneToMany relationship to LocationResident
- Add validation annotations
```

---

## Phase 6: Embeddable Classes

### 6.1 NameAndUrl Embeddable
```
Create Character_NameAndUrl embeddable class:
- Fields: name (String), url (URL with converter)
- Used for origin and location in Character entity
- Add AttributeOverride annotations in Character entity
```

---

## Phase 7: Join Table Entities

### 7.1 CharacterEpisode Entity
```
Create CharacterEpisode entity for character_episode join table:
- Use embedded ID (CharacterEpisodeId)
- Add ManyToOne to Character
- Add ManyToOne to Episode
- Make setters update the embedded ID automatically
```

### 7.2 CharacterEpisodeId Embeddable
```
Create CharacterEpisodeId embeddable ID class:
- Fields: characterId, episodeId (both Integer)
- Implement Serializable and Comparable
- Add equals, hashCode, toString
- Add static factory method
- Add comparators
```

### 7.3 EpisodeCharacter Entity
```
Create EpisodeCharacter entity for episode_character join table:
- Similar structure to CharacterEpisode but reversed
- Mark as deprecated (use CharacterEpisode instead)
```

### 7.4 LocationResident Entity
```
Create LocationResident entity for location_resident join table:
- Use embedded ID (LocationResidentId)
- Add ManyToOne to Location
- Add ManyToOne to Character (as resident)
- Make setters update the embedded ID automatically
```

### 7.5 LocationResidentId Embeddable
```
Create LocationResidentId embeddable ID class:
- Fields: locationId, residentId (both Integer)
- Similar structure to CharacterEpisodeId
```

---

## Phase 8: Special Converters

### 8.1 Episode AirDate Converter
```
Create Episode_AirDateConverter:
- Convert between LocalDate and String
- Handle date format conversion
- Used for air_date column
```

---

## Phase 9: Utilities & Constants

### 9.1 Persistence Constants
```
Create _PersistenceConstants class:
- Add constants for total counts (characters, episodes, locations)
- Private constructor to prevent instantiation
```

### 9.2 Persistence Utils
```
Create _PersistenceUtils class:
- Placeholder for future utility methods
- Private constructor to prevent instantiation
```

---

## Phase 10: Package Documentation

### 10.1 Package Info
```
Create package-info.java files:
- Document the package purpose
- Add cross-references to main entities
- Include license header
```

---

## Phase 11: Validation & Refinement

### 11.1 Nullability Analysis
```
Verify all column nullability matches database schema:
- Check @Basic(optional) matches NOT NULL constraints
- Check @Column(nullable) matches database
- Add @NotNull/@Nullable validation annotations where needed
```

### 11.2 Relationship Verification
```
Verify all relationships are correctly mapped:
- Check @ManyToOne/@OneToMany/@ManyToMany annotations
- Verify @JoinColumn names match foreign keys
- Verify @JoinTable configurations for many-to-many
```

### 11.3 Converter Registration
```
Ensure all converters are registered in persistence.xml:
- List all entity classes
- List all converter classes
- Verify auto-applying converters are marked correctly
```

---

## Phase 12: Code Quality

### 12.1 Code Analysis
```
Analyze the codebase for issues:
- Check for compilation errors
- Verify naming conventions
- Check for unused code
- Verify documentation completeness
```

### 12.2 Bug Fixes
```
Fix any identified issues:
- Correct wrong method calls in setters
- Fix incorrect converter class names
- Remove redundant annotations
- Clean up commented code
```

---

## Typical AI Interaction Pattern

### Pattern 1: Discovery & Analysis
```
"Analyze [database/file/structure] and tell me what you find"
"Extract the schema from [database]"
"List all [entities/tables/columns]"
```

### Pattern 2: Creation with Context
```
"Create [entity/converter/enum] for [table/column] based on [schema/example]"
"Generate [class] that maps to [database element]"
"Build [component] following the same pattern as [existing component]"
```

### Pattern 3: Relationship Mapping
```
"Add relationship between [Entity1] and [Entity2]"
"Map the [many-to-many/one-to-many] relationship using [join table]"
"Create join table entity for [relationship]"
```

### Pattern 4: Validation & Verification
```
"Verify [nullability/relationships/mappings] against the database"
"Check if [annotation/configuration] is correct"
"Find any issues in [file/component]"
```

### Pattern 5: Refinement
```
"Fix [issue] in [file]"
"Update [component] to match [requirement]"
"Clean up [redundant code/unused imports]"
```

---

## Key Principles for AI Instructions

### 1. Be Specific About Context
- ✅ "Create Character entity from the character table"
- ❌ "Create an entity"

### 2. Reference Existing Patterns
- ✅ "Create Location entity following the same pattern as Character"
- ❌ "Create Location entity"

### 3. Specify Requirements
- ✅ "Add ManyToMany relationship with validation annotations"
- ❌ "Add relationship"

### 4. Use Natural Language
- ✅ "Make the converter handle null values gracefully"
- ❌ "Implement null-safe conversion logic"

### 5. Iterative Refinement
- ✅ "Now add validation annotations to the Character entity"
- ❌ "Create Character entity with everything"

---

## Example Full Conversation Flow

```
1. "Analyze the rickandmortyapi.db database and show me the schema"

2. "Create a Maven project structure for a JPA persistence module"

3. "Create base classes for entities - a mapped superclass and a generic base entity"

4. "Create converter infrastructure - base classes for attribute converters"

5. "Create URL to String converter and List<URL> to String converter"

6. "Create Instant to String converter using ISO-8601 format"

7. "Analyze the character table and create Character_Status enum with values: Alive, Dead, unknown"

8. "Create Character_StatusConverter that auto-applies"

9. "Create Character entity mapping all columns from the character table"

10. "Add ManyToOne relationships from Character to Location for origin and location"

11. "Create Character_NameAndUrl embeddable class for origin and location fields"

12. "Create CharacterEpisode entity for the character_episode join table"

13. "Create CharacterEpisodeId embeddable ID class"

14. "Add ManyToMany relationship between Character and Episode"

15. "Verify all nullability constraints match the database schema"

16. "Find and fix any issues in the codebase"
```

---

## Notes on Vibe Coding Style

- **Natural Language**: Instructions read like talking to a colleague
- **Context-Aware**: Each instruction builds on previous work
- **Incremental**: Build piece by piece, not all at once
- **Verification**: Regularly check work against requirements
- **Pattern-Based**: Reuse patterns from existing code
- **Task-Oriented**: Focus on "what" not "how"
- **Iterative**: Refine and improve as you go

---

## Summary

This module was built through a series of conversational, high-level instructions to an AI assistant. Each instruction:

1. **Specifies the task** (create, analyze, verify, fix)
2. **Provides context** (which table, which entity, which relationship)
3. **References patterns** (follow existing code style)
4. **Iterates incrementally** (build one piece at a time)
5. **Validates results** (check against database, find issues)

The "vibe" is natural, conversational, and task-oriented - like pair programming with an AI that understands the domain and can generate the detailed implementation.

