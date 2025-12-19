package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

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
                    .createQuery("SELECT e.episode FROM Episode e", String.class)
                    .getResultList();
        }
        return Collections.unmodifiableList(result);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Episode_PersistenceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
