# rickandmortyapi-persistence mappings

This document provides mapping details between the Rick and Morty API JSON responses and the project's database schema
and JPA entities.

이 문서는 Rick and Morty API JSON 응답과 프로젝트의 데이터베이스 스키마 및 JPA 엔티티 간의 매핑 상세 정보를 제공합니다.

See [db_schema].

## Disclaimer

This document is maintained with the assistance of an AI/LLM. While we strive for accuracy, some details may be outdated
or incorrect. Please refer to the actual source code and database schema for the most up-to-date information.

이 문서는 AI/LLM의 도움을 받아 관리됩니다. 정확성을 기하기 위해 노력하고 있으나, 일부 세부 사항이 최신 정보와 다르거나 부정확할 수 있습니다. 가장 최신 정보는 실제 소스 코드와 데이터베이스 스키마를
참조하시기 바랍니다.

## character

Mapping for Rick and Morty characters.

Rick and Morty 캐릭터에 대한 매핑 정보입니다.

See [character_schema] and [character_1].

- origin API JSON Path: [character_1]
- table: `character`
- entity: `io.github.jinahya.rickandmortyapi.persistence.Character`

| schema            | column          | column type | attribute type         | attribute   | notes                    |
|-------------------|-----------------|-------------|------------------------|-------------|--------------------------|
| `$.id`            | `id`            | `INTEGER`   | `Integer`              | `id`        | PK                       |
| `$.name`          | `name`          | `TEXT`      | `String`               | `name`      |                          |
| `$.status`        | `status`        | `TEXT`      | `Character_Status`     | `status`    | `enum`                   |
| `$.species`       | `species`       | `TEXT`      | `Character_Species`    | `species`   | `enum`                   |
| `$.type`          | `type`          | `TEXT`      | `Character_Type`       | `type`      | `enum`                   |
| `$.gender`        | `gender`        | `TEXT`      | `Character_Gender`     | `gender`    | `enum`                   |
| `$.origin`        |                 |             | `Character_NameAndUrl` | `origin`    | `@Embedded`              |
| `$.origin.name`   | `origin_name`   | `TEXT`      | `String`               | `name`      |                          |
| `$.origin.url`    | `origin_url`    | `TEXT`      | `URL`                  | `url`       |                          |
| `$.location`      |                 |             | `Character_NameAndUrl` | `location`  | `@Embedded`              |
| `$.location.name` | `location_name` | `TEXT`      | `String`               | `name`      |                          |
| `$.location.url`  | `location_url`  | `TEXT`      | `URL`                  | `url`       |                          |
| `$.image`         | `image`         | `TEXT`      | `URL`                  | `image`     | `unique`                 |
| `$.episode`       | `episode`       | `TEXT`      | `List<URL>`            | `episode`   |                          |
| `$.url`           | `url`           | `TEXT`      | `URL`                  | `url`       | `unique`                 |
| `$.created`       | `created`       | `TEXT`      | `Instant`              | `created`   |                          |
|                   | `origin_id_`    | `INTEGER`   | `Location`             | `origin_`   | references `location.id` |
|                   | `location_id_`  | `INTEGER`   | `Location`             | `location_` | references `location.id` |
|                   |                 |             | `List<Episode>`        | `episodes_` | `@ManyToMany`            |

- `origin_id_`: A foreign key column referencing `id` of `location` table for character's origin. / 캐릭터의 출신지를 나타내는
  `location` 테이블의 `id`를 참조하는 외래 키 열입니다.
- `location_id_`: A foreign key column referencing `id` of `location` table for character's current location. / 캐릭터의 현재
  위치를 나타내는 `location` 테이블의 `id`를 참조하는 외래 키 열입니다.
- `episodes_`: A many-to-many relationship with `Episode` entity. / `Episode` 엔티티와의 다대다(N:M) 연관 관계입니다.

## episode

Mapping for Rick and Morty episodes.

Rick and Morty 에피소드에 대한 매핑 정보입니다.

See [episode_schema] and [episode_1].

- origin API JSON Path: [episode_1]
- table: `episode`
- entity: `io.github.jinahya.rickandmortyapi.persistence.Episode`

| schema         | column          | column type | attribute type    | attribute     | notes                                 |
|----------------|-----------------|-------------|-------------------|---------------|---------------------------------------|
| `$.id`         | `id`            | `INTEGER`   | `Integer`         | `id`          | PK                                    |
| `$.name`       | `name`          | `TEXT`      | `String`          | `name`        |                                       |
| `$.air_date`   | `air_date`      | `TEXT`      | `String`          | `airDate`     |                                       |
| `$.episode`    | `episode`       | `TEXT`      | `String`          | `episode`     | `unique`                              |
| `$.characters` | `characters`    | `TEXT`      | `List<URL>`       | `characters`  |                                       |
| `$.url`        | `url`           | `TEXT`      | `URL`             | `url`         | `unique`                              |
| `$.created`    | `created`       | `TEXT`      | `Instant`         | `created`     |                                       |
|                | `air_date_iso_` | `TEXT`      | `LocalDate`       | `airDateIso_` |                                       |
|                |                 |             | `List<Character>` | `characters_` | `@ManyToMany(mappedBy = "episodes_")` |

- `air_date_iso_`: A column storing air date in ISO format for internal operations. / 내부 연산을 위해 ISO 형식으로 방영일을 저장하는 열입니다.
- `characters_`: A many-to-many relationship with `Character` entity. / `Character` 엔티티와의 다대다(N:M) 연관 관계입니다.

## location

Mapping for Rick and Morty locations.

Rick and Morty 장소에 대한 매핑 정보입니다.

See [location_schema] and [location_1].

- origin API JSON Path: [location_1]
- table: `location`
- entity: `io.github.jinahya.rickandmortyapi.persistence.Location`

| schema        | column      | column type | attribute type       | attribute             | notes                                |
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

- `residents_`: A one-to-many relationship with `Character` entity for residents of this location. / 이 장소의 거주자들을 나타내는
  `Character` 엔티티와의 일대다(1:N) 연관 관계입니다.
- `originCharacters_`: A one-to-many relationship with `Character` entity whose origin is this location. / 이 장소가 출신지인
  캐릭터들을 나타내는 `Character` 엔티티와의 일대다(1:N) 연관 관계입니다.
- `locationCharacters_`: A one-to-many relationship with `Character` entity whose current location is this location. / 이
  장소가 현재 위치인 캐릭터들을 나타내는 `Character` 엔티티와의 일대다(1:N) 연관 관계입니다.

## character_episode

Join table mapping between characters and episodes.

캐릭터와 에피소드 간의 조인 테이블 매핑 정보입니다.

- origin API JSON Path: `$.episode` in [character_1]
- table: `character_episode`
- entity: `io.github.jinahya.rickandmortyapi.persistence.CharacterEpisode`

| schema | column         | column type | attribute type | attribute   | notes                         |
|--------|----------------|-------------|----------------|-------------|-------------------------------|
|        | `character_id` | `INTEGER`   | `Character`    | `character` | PK, references `character.id` |
|        | `episode_id`   | `INTEGER`   | `Episode`      | `episode`   | PK, references `episode.id`   |

- `character_id`: A primary key and foreign key column referencing `id` of `character` table. / `character` 테이블의 `id`를
  참조하는 기본 키 및 외래 키 열입니다.
- `episode_id`: A primary key and foreign key column referencing `id` of `episode` table. / `episode` 테이블의 `id`를 참조하는 기본
  키 및 외래 키 열입니다.

## episode_character

Join table mapping between episodes and characters.

에피소드와 캐릭터 간의 조인 테이블 매핑 정보입니다.

- origin API JSON Path: `$.characters` in [episode_1]
- table: `episode_character`
- entity: `io.github.jinahya.rickandmortyapi.persistence.EpisodeCharacter`

| schema | column         | column type | attribute type | attribute   | notes                         |
|--------|----------------|-------------|----------------|-------------|-------------------------------|
|        | `episode_id`   | `INTEGER`   | `Episode`      | `episode`   | PK, references `episode.id`   |
|        | `character_id` | `INTEGER`   | `Character`    | `character` | PK, references `character.id` |

- `episode_id`: A primary key and foreign key column referencing `id` of `episode` table. / `episode` 테이블의 `id`를 참조하는 기본
  키 및 외래 키 열입니다.
- `character_id`: A primary key and foreign key column referencing `id` of `character` table. / `character` 테이블의 `id`를
  참조하는 기본 키 및 외래 키 열입니다.

## location_resident

Join table mapping between locations and residents (characters).

장소와 거주자(캐릭터) 간의 조인 테이블 매핑 정보입니다.

- origin API JSON Path: `$.residents` in [location_1]
- table: `location_resident`
- entity: `io.github.jinahya.rickandmortyapi.persistence.LocationResident`

| schema | column        | column type | attribute type | attribute  | notes                         |
|--------|---------------|-------------|----------------|------------|-------------------------------|
|        | `location_id` | `INTEGER`   | `Location`     | `location` | PK, references `location.id`  |
|        | `resident_id` | `INTEGER`   | `Character`    | `resident` | PK, references `character.id` |

- `location_id`: A primary key and foreign key column referencing `id` of `location` table. / `location` 테이블의 `id`를 참조하는
  기본 키 및 외래 키 열입니다.
- `resident_id`: A primary key and foreign key column referencing `id` of `character` table. / `character` 테이블의 `id`를
  참조하는 기본 키 및 외래 키 열입니다.

---

[db_schema]: https://github.com/jinahya/rickandmortyapi-db/blob/develop/rickandmortyapi-db.sql

[character_schema]: https://rickandmortyapi.com/documentation/#character-schema

[character_1]: https://rickandmortyapi.com/api/character/1

[episode_schema]: https://rickandmortyapi.com/documentation/#episode-schema

[episode_1]: https://rickandmortyapi.com/api/episode/1

[location_schema]: https://rickandmortyapi.com/documentation/#location-schema

[location_1]: https://rickandmortyapi.com/api/location/1
