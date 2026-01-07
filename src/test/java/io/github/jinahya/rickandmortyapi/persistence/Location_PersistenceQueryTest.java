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

import io.github.jinahya.rickandmortyapi.persistence.mapped.__ColumnEnumComparator;
import jakarta.persistence.criteria.Nulls;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Location_PersistenceQueryTest
        extends _BaseEntity_Persistence_<Location, Integer> {

    private static <R> R applyFirstResultAndMaxResults(final IntFunction<? extends IntFunction<? extends R>> function) {
        Objects.requireNonNull(function, "function is null");
        final var firstResult = ThreadLocalRandom.current().nextInt(0, _PersistenceConstants.NUMBER_OF_ALL_LOCATIONS);
        final var maxResults = ThreadLocalRandom.current().nextInt(1, 100);
        return function.apply(firstResult).apply(maxResults);
    }

    private static Stream<Arguments> getFirstResultAndMaxResultsArgumentsStream() {
        return applyFirstResultAndMaxResults(fr -> mr -> Stream.of(Arguments.of(fr, mr)));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Location_PersistenceQueryTest() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("SelectList__OrderByTypeAscNullsFirstIdAsc")
    @Nested
    class SelectList__OrderByTypeNullsFirstIdAsc_Test {

        private static void verify(final List<Location> entityList) {
            final var comparator = Comparator.comparing(
                            Location::getType,
                            Comparator.nullsFirst(
                                    __ColumnEnumComparator.comparingColumnValue(Comparator.naturalOrder())
                            )
                    )
                    .thenComparing(Location.COMPARING_ID);
            assertThat(entityList)
                    .isNotNull()
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .isSortedAccordingTo(comparator);
        }

        @Test
        void namedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        "Location.SelectList__OrderByTypeAscNullsFirstIdAsc",
                        entityClass
                );
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }

        @Test
        void queryLanguage__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createQuery(
                        """
                                SELECT l
                                FROM Location l
                                ORDER BY l.type ASC NULLS FIRST,
                                         l.id ASC""",
                        entityClass
                );
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }

        @Test
        void criteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(
                        builder.asc(root.get(Location_.type), Nulls.FIRST),
                        builder.asc(root.get(Location_.id))
                );
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }
    }

    @DisplayName("SelectList__OrderByTypeAscNullsLastIdAsc")
    @Nested
    class SelectList__OrderByTypeNullsLastIdAsc_Test {

        private static void verify(final List<Location> entityList) {
            assertThat(entityList)
                    .isNotNull()
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .isSortedAccordingTo(
                            Comparator.comparing(
                                            Location::getType,
                                            Comparator.nullsLast(
                                                    __ColumnEnumComparator.comparingColumnValue(
                                                            Comparator.naturalOrder()
                                                    )
                                            )
                                    )
                                    .thenComparing(Location.COMPARING_ID)
                    );
        }

        @Test
        void namedQuery__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createNamedQuery(
                        "Location.SelectList__OrderByTypeAscNullsLastIdAsc",
                        entityClass
                );
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }

        @Test
        void queryLanguage__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createQuery(
                        """
                                SELECT l
                                FROM Location l
                                ORDER BY l.type ASC NULLS LAST,
                                         l.id ASC""",
                        entityClass
                );
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }

        @Test
        void criteriaApi__() {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(
                        builder.asc(root.get(Location_.type), Nulls.LAST),
                        builder.asc(root.get(Location_.id))
                );
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(entityList);
        }
    }
}
