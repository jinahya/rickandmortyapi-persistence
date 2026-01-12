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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// 명권님
@Slf4j
@SuppressWarnings({
    "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_FindByOrigin__PersistenceTest
    extends _BaseEntity_Persistence_<Character, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_FindByOrigin__PersistenceTest() {
        super(Character.class, Integer.class);
    }

    @Nested
    class UsingId_Test {

        private static void verify(final int originId, final List<Character> entityList) {
            assertThat(entityList)
                .isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Character.COMPARING_ID)
                .extracting(character -> {
                    assert character.getOrigin_() != null;
                    return character.getOrigin_().getId();
                })
                .containsOnly(originId)
            ;
        }

        // 1, 2, 3 not empty, 4 empty
        @ValueSource(ints = {
            1, 2, 3
        })
        @ParameterizedTest
        void _QueryLanguage_(final int origin_id) {
            final var entityList = applyEntityManager(em -> {
                final var query = em.createQuery(
                    """
                        SELECT  c
                        FROM    Character c
                        WHERE   c.origin_.id = :origin_id
                        ORDER BY c.id ASC""",
                    entityClass
                    /**
                     * entityClass > Character :
                     * _BaseEntity_Persistence_<ENTITY extends _BaseEntity<ID>, ID> extends __Base_PersistenceTest<ENTITY>
                     */
                );
                query.setParameter("origin_id", origin_id);
                return query.getResultList();
            });
            verify(origin_id, entityList);
        }

        @ValueSource(ints = {
            1, 2, 3
        })
        @ParameterizedTest
        void _CriteriaApi_(final int origin_id) {
            final var entityList = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.where(
                    builder.equal(root.get(Character_.origin_).get(Location_.id), origin_id)
                );
                query.orderBy(builder.asc(root.get(Character_.id)));
                return em.createQuery(query).getResultList();
            });
            verify(origin_id, entityList);
        }
    }

    @Nested
    class UsingEntity_Test {

        private static void verify(final int locationId, final List<Character> entityList) {
            assertThat(entityList)
                .isNotNull()
                .isNotEmpty()
                .doesNotContainNull()
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Character.COMPARING_ID)
                .extracting(character -> {
                    assert character.getOrigin_() != null;
                    return character.getOrigin_().getId();
                })
                .containsOnly(locationId)
            ;
        }

        @Test
        void _QueryLanguage_() {
            final var originLocationId = 1;
            final var result = applyEntityManager(em -> {
                final var origin_ = em.find(Location.class, originLocationId);
                final var query = em.createQuery(
                    """
                        SELECT c
                        FROM Character c
                        WHERE c.origin_ = :origin_
                        ORDER BY c.id ASC
                        """,
                    entityClass
                );
                query.setParameter("origin_", origin_);
                return query.getResultList();
            });
            verify(originLocationId, result);
        }

        @Test
        void _CriteriaApi_() {
            final var originLocationId = 1;
            final var result = applyEntityManager(em -> {
                final var origin_ = em.find(Location.class, originLocationId);
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Character.class);
                final var root = query.from(Character.class);
                query.select(root);
                query.where(builder.equal(root.get(Character_.origin_), origin_));
                query.orderBy(builder.asc(root.get(Character_.id)));
                return em.createQuery(query).getResultList();
            });
            verify(originLocationId, result);
        }
    }
}
