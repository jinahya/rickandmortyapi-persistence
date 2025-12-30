# 5. API-to-Database-to-Entity Mappings

[← Previous: Persistence Layer Analysis](04-persistence-layer-analysis.md) | [Index](index.md) | [Next: Code Quality & Architectural Review →](06-code-quality-architectural-review.md)

---

This section provides detailed mapping information between the Rick and Morty API JSON responses, database schema, and
JPA entities.

### Disclaimer

This document is maintained with the assistance of an AI/LLM. While we strive for accuracy, some details may be outdated
or incorrect. Please refer to the actual source code and database schema for the most up-to-date information.

### Character Mappings

Mapping for characters.

| Property | Detail                                                                                                                                                             |
|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | [`character/1`](https://rickandmortyapi.com/api/character/1)                                                                                                       |
| Table    | `character`                                                                                                                                                        |
| Entity   | [`Character.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/Character.java) |

| Schema            | Column          | Column Type | Attribute Type         | Attribute       | Notes                    |
|-------------------|-----------------|-------------|------------------------|-----------------|--------------------------|
| `$.id`            | `id`            | `INTEGER`   | `Integer`              | `id`            | PK                       |
| `$.name`          | `name`          | `TEXT`      | `String`               | `name`          |                          |
| `$.status`        | `status`        | `TEXT`      | `Character_Status`     | `status`        | `enum`                   |
| `$.species`       | `species`       | `TEXT`      | `Character_Species`    | `species`       | `enum`                   |
| `$.type`          | `type`          | `TEXT`      | `Character_Type`       | `type`          | `enum`                   |
| `$.gender`        | `gender`        | `TEXT`      | `Character_Gender`     | `gender`        | `enum`                   |
| `$.origin`        |                 |             | `Character_NameAndUrl` | `origin`        | `@Embedded`              |
| `$.origin.name`   | `origin_name`   | `TEXT`      | `String`               | `origin.name`   |                          |
| `$.origin.url`    | `origin_url`    | `TEXT`      | `URL`                  | `origin.url`    |                          |
| `$.location`      |                 |             | `Character_NameAndUrl` | `location`      | `@Embedded`              |
| `$.location.name` | `location_name` | `TEXT`      | `String`               | `location.name` |                          |
| `$.location.url`  | `location_url`  | `TEXT`      | `URL`                  | `location.url`  |                          |
| `$.image`         | `image`         | `TEXT`      | `URL`                  | `image`         | `unique`                 |
| `$.episode`       | `episode`       | `TEXT`      | `List<URL>`            | `episode`       |                          |
| `$.url`           | `url`           | `TEXT`      | `URL`                  | `url`           | `unique`                 |
| `$.created`       | `created`       | `TEXT`      | `Instant`              | `created`       |                          |
|                   | `origin_id_`    | `INTEGER`   | `Location`             | `origin_`       | references `location.id` |
|                   | `location_id_`  | `INTEGER`   | `Location`             | `location_`     | references `location.id` |
|                   |                 |             | `List<Episode>`        | `episodes_`     | `@ManyToMany`            |

- `origin_id_`: A foreign key column referencing `id` of `location` table for character's origin.
- `location_id_`: A foreign key column referencing `id` of `location` table for character's current location.
- `episodes_`: A many-to-many relationship with `Episode` entity.

### Episode Mappings

Mapping for episodes.

| Property | Detail                                                                                                                                                         |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | [`episode/1`](https://rickandmortyapi.com/api/episode/1)                                                                                                       |
| Table    | `episode`                                                                                                                                                      |
| Entity   | [`Episode.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/Episode.java) |

| Schema         | Column          | Column Type | Attribute Type    | Attribute     | Notes                                 |
|----------------|-----------------|-------------|-------------------|---------------|---------------------------------------|
| `$.id`         | `id`            | `INTEGER`   | `Integer`         | `id`          | PK                                    |
| `$.name`       | `name`          | `TEXT`      | `String`          | `name`        |                                       |
| `$.air_date`   | `air_date`      | `TEXT`      | `LocalDate`       | `airDate`     |                                       |
| `$.episode`    | `episode`       | `TEXT`      | `String`          | `episode`     | `unique`                              |
| `$.characters` | `characters`    | `TEXT`      | `List<URL>`       | `characters`  |                                       |
| `$.url`        | `url`           | `TEXT`      | `URL`             | `url`         | `unique`                              |
| `$.created`    | `created`       | `TEXT`      | `Instant`         | `created`     |                                       |
|                | `air_date_iso_` | `TEXT`      | `LocalDate`       | `airDateIso_` |                                       |
|                |                 |             | `List<Character>` | `characters_` | `@ManyToMany(mappedBy = "episodes_")` |

- `air_date_iso_`: A column storing air date in ISO-8601 format for internal operations.
- `characters_`: A many-to-many relationship with `Character` entity.

### Location Mappings

Mapping for locations.

| Property | Detail                                                                                                                                                           |
|----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | [`location/1`](https://rickandmortyapi.com/api/location/1)                                                                                                       |
| Table    | `location`                                                                                                                                                       |
| Entity   | [`Location.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/Location.java) |

| Schema        | Column      | Column Type | Attribute Type       | Attribute             | Notes                                |
|---------------|-------------|-------------|----------------------|-----------------------|--------------------------------------|
| `$.id`        | `id`        | `INTEGER`   | `Integer`            | `id`                  | PK                                   |
| `$.name`      | `name`      | `TEXT`      | `String`             | `name`                |                                      |
| `$.type`      | `type`      | `TEXT`      | `Location_Type`      | `type`                | `enum`                               |
| `$.dimension` | `dimension` | `TEXT`      | `Location_Dimension` | `dimension`           | `enum`                               |
| `$.residents` | `residents` | `TEXT`      | `List<URL>`          | `residents`           |                                      |
| `$.url`       | `url`       | `TEXT`      | `URL`                | `url`                 | `unique`                             |
| `$.created`   | `created`   | `TEXT`      | `Instant`            | `created`             |                                      |
|               |             |             | `List<Character>`    | `residents_`          | `@OneToMany`                         |
|               |             |             | `List<Character>`    | `originCharacters_`   | `@OneToMany(mappedBy = "origin_")`   |
|               |             |             | `List<Character>`    | `locationCharacters_` | `@OneToMany(mappedBy = "location_")` |

- `residents_`: A one-to-many relationship with `Character` entity for residents of this location.
- `originCharacters_`: A one-to-many relationship with `Character` entity whose origin is this location.
- `locationCharacters_`: A one-to-many relationship with `Character` entity whose current location is this location.

### Join Table Mappings

#### character_episode

Join table mapping between characters and episodes.

| Property | Detail                                                                                                                                                                           |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | `$.episode` in [`character/1`](https://rickandmortyapi.com/api/character/1)                                                                                                      |
| Table    | `character_episode`                                                                                                                                                              |
| Entity   | [`CharacterEpisode.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/CharacterEpisode.java) |

| Schema | Column         | Column Type | Attribute Type | Attribute   | Notes                         |
|--------|----------------|-------------|----------------|-------------|-------------------------------|
|        | `character_id` | `INTEGER`   | `Character`    | `character` | PK, references `character.id` |
|        | `episode_id`   | `INTEGER`   | `Episode`      | `episode`   | PK, references `episode.id`   |

- `character_id`: A primary key and foreign key column referencing `id` of `character` table.
- `episode_id`: A primary key and foreign key column referencing `id` of `episode` table.

#### episode_character

Join table mapping between episodes and characters.

| Property | Detail                                                                                                                                                                           |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | `$.characters` in [`episode/1`](https://rickandmortyapi.com/api/episode/1)                                                                                                       |
| Table    | `episode_character`                                                                                                                                                              |
| Entity   | [`EpisodeCharacter.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/EpisodeCharacter.java) |

| Schema | Column         | Column Type | Attribute Type | Attribute   | Notes                         |
|--------|----------------|-------------|----------------|-------------|-------------------------------|
|        | `episode_id`   | `INTEGER`   | `Episode`      | `episode`   | PK, references `episode.id`   |
|        | `character_id` | `INTEGER`   | `Character`    | `character` | PK, references `character.id` |

- `episode_id`: A primary key and foreign key column referencing `id` of `episode` table.
- `character_id`: A primary key and foreign key column referencing `id` of `character` table.

#### location_resident

Join table mapping between locations and residents (characters).

| Property | Detail                                                                                                                                                                           |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API      | `$.residents` in [`location/1`](https://rickandmortyapi.com/api/location/1)                                                                                                      |
| Table    | `location_resident`                                                                                                                                                              |
| Entity   | [`LocationResident.java`](https://github.com/jinahya/rickandmortyapi-persistence/blob/develop/src/main/java/io/github/jinahya/rickandmortyapi/persistence/LocationResident.java) |

| Schema | Column        | Column Type | Attribute Type | Attribute  | Notes                         |
|--------|---------------|-------------|----------------|------------|-------------------------------|
|        | `location_id` | `INTEGER`   | `Location`     | `location` | PK, references `location.id`  |
|        | `resident_id` | `INTEGER`   | `Character`    | `resident` | PK, references `character.id` |

- `location_id`: A primary key and foreign key column referencing `id` of `location` table.
- `resident_id`: A primary key and foreign key column referencing `id` of `character` table.

**Reference Links**:

- [Database Schema](https://github.com/jinahya/rickandmortyapi-db/blob/develop/rickandmortyapi-db.sql)
- [Character API Schema](https://rickandmortyapi.com/documentation/#character-schema)
- [Episode API Schema](https://rickandmortyapi.com/documentation/#episode-schema)
- [Location API Schema](https://rickandmortyapi.com/documentation/#location-schema)

---

[← Previous: Persistence Layer Analysis](04-persistence-layer-analysis.md) | [Index](index.md) | [Next: Code Quality & Architectural Review →](06-code-quality-architectural-review.md)
