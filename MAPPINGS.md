# rickandmortyapi-persistence

See [db_schema].

## character

See [character_schema].

| schema                        | column          | column type | attribute type              | attribute                   | notes         |
|-------------------------------|-----------------|-------------|-----------------------------|-----------------------------|---------------|
| `$.id`                        | `id`            | `INTEGER`   | `Integer`                   | `id`                        | `primary key` |
| `$.name`                      | `name`          | `TEXT`      | `String`                    | `name`                      |               |
| `$.status`                    | `species`       | `TEXT`      | `Character_Species`         | `species`                   |               |
| `$.species`                   | `status`        | `TEXT`      | `Character_Status`          | `status`                    |               |
| `$.type`                      | `type`          | `TEXT`      | `Character_Type`            | `type`                      |               |
| `$.gender`                    | `gender`        | `TEXT`      | `Character_Gender`          | `gender`                    |               |
| `$.origin`                    |                 |             | `Character_NameAndLocation` | `origin`                    | `@Embedded`   |
| &nbsp;&nbsp;&nbsp;&nbsp;`$.origin.name`   | `origin_name`   | `TEXT`      | &nbsp;&nbsp;&nbsp;&nbsp;`String`        | &nbsp;&nbsp;&nbsp;&nbsp;`name`   |               |
| &nbsp;&nbsp;&nbsp;&nbsp;`$.origin.url`    | `origin_url`    | `TEXT`      | &nbsp;&nbsp;&nbsp;&nbsp;`URL`           | &nbsp;&nbsp;&nbsp;&nbsp;`url`    |               |
| `$.location`                  |                 |             | `Character_NameLocation`    | `location`                  |               |
| &nbsp;&nbsp;&nbsp;&nbsp;`$.location.name` | `location_name` | `TEXT`      | &nbsp;&nbsp;&nbsp;&nbsp;`String`        | &nbsp;&nbsp;&nbsp;&nbsp;`name` |               |
| &nbsp;&nbsp;&nbsp;&nbsp;`$.location.url`  | `location_url`  | `TEXT`      | &nbsp;&nbsp;&nbsp;&nbsp;`URL`           | &nbsp;&nbsp;&nbsp;&nbsp;`url` |               |
| `$.image`                     | `image`         | `TEXT`      | `URL`                       | `image`                     |               |
| `$.episode`                   | `episode`       | `TEXT`      | `List&lt;URL&gt;`           | `episode`                   |               |
| `$.url`                       | `url`           | `TEXT`      | `URL`                       | `url`                       |               |
| `$.created`                   | `created`       | `TEXT`      | `Instant`                   | `created`                   |               |
|                               | `origin_id_`    | `INTEGER`   |                             |                             | `foreign key` |
|                               | `location_id_`  | `INTEGER`   |                             |                             | `foreign key` |

[db_schema]: https://github.com/jinahya/rickandmortyapi-db/blob/develop/rickandmortyapi-db.sql

[character_schema]: https://rickandmortyapi.com/documentation/#character-schema
