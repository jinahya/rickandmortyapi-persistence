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
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
final class Character_PersistenceTestUtils {

    static List<Character> getAllCharacters(final EntityManager entityManager) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return entityManager
                    .createQuery("""
                                         SELECT c
                                         FROM Character c
                                         ORDER BY c.id ASC""",
                                 Character.class
                    )
                    .getResultList();
        }
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(Character.class);
        final var root = query.from(Character.class);
        query.select(root);
        query.orderBy(builder.asc(root.get(Character_.id)));
        return entityManager.createQuery(query).getResultList();
    }

    static List<Character> getAllCharacters(final EntityManager entityManager, final Collection<Integer> characterIds) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return entityManager
                    .createQuery("""
                                         SELECT c
                                         FROM Character c
                                         WHERE c.id IN :characterIds
                                         ORDER BY c.id ASC""",
                                 Character.class
                    )
                    .setParameter("characterIds", characterIds)
                    .getResultList();
        }
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(Character.class);
        final var root = query.from(Character.class);
        query.select(root);
        query.where(root.get(Character_.id).in(characterIds));
        query.orderBy(builder.asc(root.get(Character_.id)));
        return entityManager.createQuery(query).getResultList();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private Character_PersistenceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
