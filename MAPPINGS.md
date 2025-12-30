# rickandmortyapi-persistence

See [db_schema].

## character

See [character_schema] and [character_1].

| schema            | column          | column type | attribute type              | attribute  | notes         |
|-------------------|-----------------|-------------|-----------------------------|------------|---------------|
| `$.id`            | `id`            | `INTEGER`   | `Integer`                   | `id`       | `primary key` |
| `$.name`          | `name`          | `TEXT`      | `String`                    | `name`     |               |
| `$.status`        | `species`       | `TEXT`      | `Character_Species`         | `species`  | `enum`        |
| `$.species`       | `status`        | `TEXT`      | `Character_Status`          | `status`   | `enum`        |
| `$.type`          | `type`          | `TEXT`      | `Character_Type`            | `type`     | `enum`        |
| `$.gender`        | `gender`        | `TEXT`      | `Character_Gender`          | `gender`   | `enum`        |
| `$.origin`        |                 |             | `Character_NameAndLocation` | `origin`   | `@Embedded`   |
| `$.origin.name`   | `origin_name`   | `TEXT`      | `String`                    | `name`     |               |
| `$.origin.url`    | `origin_url`    | `TEXT`      | `URL`                       | `url`      |               |
| `$.location`      |                 |             | `Character_NameLocation`    | `location` | `@Embedded`   |
| `$.location.name` | `location_name` | `TEXT`      | `String`                    | `name`     |               |
| `$.location.url`  | `location_url`  | `TEXT`      | `URL`                       | `url`      |               |
| `$.image`         | `image`         | `TEXT`      | `URL`                       | `image`    | `unique`      |
| `$.episode`       | `episode`       | `TEXT`      | `List&lt;URL&gt;`           | `episode`  |               |
| `$.url`           | `url`           | `TEXT`      | `URL`                       | `url`      | `unique`      |
| `$.created`       | `created`       | `TEXT`      | `Instant`                   | `created`  |               |
|                   | `origin_id_`    | `INTEGER`   |                             |            | `foreign key` |
|                   | `location_id_`  | `INTEGER`   |                             |            | `foreign key` |

## episode

See [episode_schema] and [episode_1].

| schema         | column          | column type | attribute type          | attribute     | notes         |
|----------------|-----------------|-------------|-------------------------|---------------|---------------|
| `$.id`         | `id`            | `INTEGER`   | `Integer`               | `id`          | `primary key` |
| `$.name`       | `name`          | `TEXT`      | `String`                | `name`        |               |
| `$.air_date`   | `air_date`      | `TEXT`      | `LocalDate`             | `airDate`     |               |
| `$.episode`    | `episode`       | `TEXT`      | `String`                | `episode`     | `unique`      |
| `$.characters` | `characters`    | `TEXT`      | `List&lt;URL&gt;`       | `characters`  |               |
| `$.url`        | `url`           | `TEXT`      | `URL`                   | `url`         | `unique`      |
| `$.created`    | `created`       | `TEXT`      | `Instant`               | `created`     |               |
|                | `air_date_iso_` | `TEXT`      | `LocalDate`             | `airDateIso_` |               |
|                |                 |             | `List&lt;Character&gt;` | `characters`  |               |

## location

See [location_schema] and [location_1].

| schema        | column      | column type | attribute type       | attribute   | notes         |
|---------------|-------------|-------------|----------------------|-------------|---------------|
| `$.id`        | `id`        | `INTEGER`   | `Integer`            | `id`        | `primary key` |
| `$.name`      | `name`      | `TEXT`      | `String`             | `name`      |               |
| `$.type`      | `type`      | `TEXT`      | `Location_Type`      | `type`      | `enum`        |
| `$.dimension` | `dimension` | `TEXT`      | `Location_Dimension` | `dimension` | `enum`        |
| `$.residents` | `residents` | `TEXT`      | `List&lt;URL&gt;`    | `residents` |               |
| `$.url`       | `url`       | `TEXT`      | `URL`                | `url`       | `unique`      |
| `$.created`   | `created`   | `TEXT`      | `Instant`            | `created`   |               |

[db_schema]: https://github.com/jinahya/rickandmortyapi-db/blob/develop/rickandmortyapi-db.sql

[character_schema]: https://rickandmortyapi.com/documentation/#character-schema

[character_1]: https://rickandmortyapi.com/api/character/1

[episode_schema]: https://rickandmortyapi.com/documentation/#episode-schema

[episode_1]: https://rickandmortyapi.com/api/episode/1

[location_schema]: https://rickandmortyapi.com/documentation/#location-schema

[location_1]: https://rickandmortyapi.com/api/location/1
