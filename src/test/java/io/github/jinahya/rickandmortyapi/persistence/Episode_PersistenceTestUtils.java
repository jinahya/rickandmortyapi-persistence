package io.github.jinahya.rickandmortyapi.persistence;

/*-
 * #%L
 * rickandmortyapi-persistence
 * %%
 * Copyright (C) 2025 GitHub, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
