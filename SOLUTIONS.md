# Solutions

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