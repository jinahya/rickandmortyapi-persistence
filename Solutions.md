# Solutions

## Character

**Select all charcters order by `id`**

```sql
SELECT *
FROM character
ORDER BY id ASC
```

```jpaql
SELECT c
FROM Character c
ORDER BY c.id ASC
```

**Select all characters which each `name` equals to given value**

Note that the `name` column is not a `UNIQUE` column.

```sql
SELECT *
FROM character
WHERE name = ? -- 'Rick Sanchez', e.g.
ORDER BY id ASC
```

```jpaql
SELECT c
FROM Character c
WHERE c.name = :name
ORDER BY c.id ASC
```

**Select all characters which each `name` matches to given pattern**

```sqlite
SELECT *
FROM character
WHERE name LIKE ? -- '%Smith%', e.g.
ORDER BY id ASC
```
```jpaql
SELECT c
FROM Character c
WHERE c.name LIKE :namePattern
ORDER BY c.id ASC
```

**Select all characters which each `status` equals to given value**

```sqlite
SELECT *
FROM character
WHERE status = ? -- 'Alive', e.g.
ORDER BY id ASC
```
```jpaql
SELECT c
FROM Character c
WHERE c.status = :status
ORDER BY c.id ASC
```
```jpaql
SELECT c
FROM Character c
WHERE c.status = io.github.jinahya.rickmortyapi.persistence.Character.Status.ALIVE
```

**Select all characters which each `status` is in**

```sqlite
SELECT *
FROM character
WHERE status IN ? -- ('Alive', 'Dead'), e.g.
```

```jpaql
SELECT c
FROM Character c
WHERE c.status IN :statuses
```

```jpaql
SELECT c
FROM Character c
WHERE c.status IN (io.github.jinahya.rickmortyapi.persistence.Character.Status.ALIVE,
                   io.github.jinahya.rickmortyapi.persistence.Character.Status.ALIVE)
```

## Episode

### Find the `Episode` whose `episode` matches to given

```sql
SELECT *
FROM episode
WHERE episode = ?
```

```jpaql
SELECT e
FROM Episode e
WHERE e.episode = :episode
```

### List up `Episode` order by `airDateIso_` in ascending order

```sql
SELECT *
FROM episode
ORDER BY air_date_iso_ ASC
```

```jpaql
SELECT e
FROM Episode e
ORDER BY e.airDateIso_ ASC
```

### List up `Episode` order by number of `characters_` in descending order

```sql
SELECT id,
       LENGTH(characters) - LENGTH(REPLACE(characters, ',', '')) + 1 AS count
FROM episode
ORDER BY count DESC
```

```jpaql
SELECT e, SIZE(e.characters_) AS count
FROM Episode e
ORDER BY count DESC
```

```sql
SELECT episode_id, COUNT(character_id) AS count
FROM episode_character
GROUP BY episode_id
ORDER BY count DESC
```

```jpaql
SELECT e, COUNT(e.character) AS count
FROM EpisodeCharacter e
GROUP BY e.episode
ORDER BY count DESC
```