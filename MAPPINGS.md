# rickandmortyapi-persistence

See [db_schema].

## character

See [character_schema].

| schema            | column          | column type | attribute type              | attribute  | notes         |
|-------------------|-----------------|-------------|-----------------------------|------------|---------------|
| `$.id`            | `id`            | `INTEGER`   | `Integer`                   | `id`       | `primary key` |
| `$.name`          | `name`          | `TEXT`      | `String`                    | `name`     |               |
| `$.status`        | `species`       | `TEXT`      | `Character_Species`         | `species`  |               |
| `$.species`       | `status`        | `TEXT`      | `Character_Status`          | `status`   |               |
| `$.type`          | `type`          | `TEXT`      | `Character_Type`            | `type`     |               |
| `$.gender`        | `gender`        | `TEXT`      | `Character_Gender`          | `gender`   |               |
| `$.origin`        |                 |             | `Character_NameAndLocation` | `origin`   | `@Embedded`   |
| `$.origin.name`   | `origin_name`   | `TEXT`      | `String`                    | `name`     |               |
| `$.origin.url`    | `origin_url`    | `TEXT`      | `URL`                       | `url`      |               |
| `$.location`      |                 |             | `Character_NameLocation`    | `location` | `@Embedded`   |
| `$.location.name` | `location_name` | `TEXT`      | `String`                    | `name`     |               |
| `$.location.url`  | `location_url`  | `TEXT`      | `URL`                       | `url`      |               |
| `$.image`         | `image`         | `TEXT`      | `URL`                       | `image`    |               |
| `$.episode`       | `episode`       | `TEXT`      | `List&lt;URL&gt;`           | `episode`  |               |
| `$.url`           | `url`           | `TEXT`      | `URL`                       | `url`      |               |
| `$.created`       | `created`       | `TEXT`      | `Instant`                   | `created`  |               |
|                   | `origin_id_`    | `INTEGER`   |                             |            | `foreign key` |
|                   | `location_id_`  | `INTEGER`   |                             |            | `foreign key` |

[db_schema]: https://github.com/jinahya/rickandmortyapi-db/blob/develop/rickandmortyapi-db.sql

[character_schema]: https://rickandmortyapi.com/documentation/#character-schema
