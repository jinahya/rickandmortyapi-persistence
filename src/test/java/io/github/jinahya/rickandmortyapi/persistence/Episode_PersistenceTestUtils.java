package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
final class Episode_PersistenceTestUtils {

    private static volatile List<String> episodeList;

    /**
     * Returns an unmodifiable list of {@value Episode_#EPISODE} attribute values.
     *
     * @param entityManager an entity manager.
     * @return an unmodifiable list of {@value Episode_#EPISODE} attribute values.
     */
    static List<String> getEpisodeList(final EntityManager entityManager) {
        var result = episodeList;
        if (result == null) {
            episodeList = result = entityManager
                    .createQuery("""
                                         SELECT e.episode
                                         FROM Episode e
                                         ORDER BY e.id ASC""",
                                 String.class
                    )
                    .getResultList();
        }
        return Collections.unmodifiableList(result);
    }

    static List<Episode> getEpisodesIdIn(final EntityManager entityManager, final Collection<Integer> episodeIds) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return entityManager
                    .createQuery(
                            """
                                    SELECT e
                                    FROM Episode e
                                    WHERE e.id IN : episodeIds""",
                            Episode.class
                    )
                    .setParameter("episodeIds", episodeIds)
                    .getResultList();
        }
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(Episode.class);
        final var root = query.from(Episode.class);
        query.select(root);
        query.where(root.get(Episode_.id).in(episodeIds));
        return entityManager.createQuery(query).getResultList();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Episode_PersistenceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
