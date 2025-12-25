# Solutions

## Find characters who appeared in all specified episodes

```sqlite
SELECT c.*
FROM character c
         JOIN character_episode ce ON c.id = ce.character_id
WHERE ce.episode_id IN (1, 2, 3)
GROUP BY c.id
HAVING COUNT(DISTINCT ce.episode_id) = 3
ORDER BY c.id ASC
;
```

```jpaql
SELECT c
FROM Character c
         JOIN c.episodes_ ce
WHERE ce.id IN (1, 2, 3)
GROUP BY c
HAVING COUNT(DISTINCT ce.id) = 3
ORDER BY c.id ASC
```

```sqlite
SELECT c.*
FROM character_episode ce
         JOIN character c ON c.id = ce.character_id
WHERE ce.episode_id IN (1, 2, 3)
GROUP BY ce.character_id
HAVING COUNT(DISTINCT ce.episode_id) = 3
ORDER BY ce.character_id ASC
;
```

```jpaql
SELECT c
FROM CharacterEpisode ce
         JOIN ce.character c
WHERE ce.id.episodeId IN (1, 2, 3)
GROUP BY c
HAVING COUNT(DISTINCT ce.id.episodeId) = 3
ORDER BY c.id ASC
```