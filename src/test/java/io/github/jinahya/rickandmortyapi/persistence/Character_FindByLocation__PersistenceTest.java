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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_FindByLocation__PersistenceTest
        extends _BaseEntity_Persistence_<Character, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_FindByLocation__PersistenceTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private <R> R applyFirstResultAndMaxResults(
            final Function<? super Integer, ? extends Function<? super Integer, ? extends R>> function) {
        final var firstResult = ThreadLocalRandom.current().nextBoolean()
                ? null
                : ThreadLocalRandom.current().nextInt(1, 10);
        final var maxResults =
                ThreadLocalRandom.current().nextBoolean()
                        ? null
                        : ThreadLocalRandom.current().nextInt(1, 10);
        return function
                .apply(firstResult)
                .apply(maxResults);
    }

    private void acceptFirstResultAndMaxResults(
            final Function<? super Integer, ? extends Consumer<? super Integer>> function) {
        applyFirstResultAndMaxResults(fr -> mr -> {
            function.apply(fr).accept(mr);
            return null;
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class UsingId_Test {

        private static void verify(final int location_Id, final Integer firstResult, final Integer maxResults,
                                   final List<Character> entityList) {
            if (firstResult != null && entityList.isEmpty()) {
                return;
            }
            assertThat(entityList)
                    .hasSizeLessThanOrEqualTo(Optional.ofNullable(maxResults).orElse(Integer.MAX_VALUE))
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .isSortedAccordingTo(Character.COMPARING_ID)
                    .extracting(Character::getLocation_)
                    .as("extracted {location_}s")
                    .doesNotContainNull()
                    .extracting(Location::getId)
                    .as("extracted {location_.id}s")
                    .containsOnly(location_Id)
            ;
        }

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _QueryLanguage_(final int location_Id) {
            // --------------------------------------------------------------------------------------------------- given
            acceptFirstResultAndMaxResults(fr -> mr -> {
                // ------------------------------------------------------------------------------------------------ when
                final var result = applyEntityManager(em -> {
                    final var query = em.createQuery(
                            """
                                    SELECT  c
                                    FROM Character c
                                    WHERE c.location_.id = :location_Id
                                    ORDER BY c.id ASC""",
                            entityClass
                    );
                    query.setParameter("location_Id", location_Id);
                    Optional.ofNullable(fr).ifPresent(query::setFirstResult);
                    Optional.ofNullable(mr).ifPresent(query::setMaxResults);
                    return query.getResultList();
                });
                // ------------------------------------------------------------------------------------------------ then
                verify(location_Id, fr, mr, result);
            });
        }

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _CriteriaApi_(final int location_Id) {
            // --------------------------------------------------------------------------------------------------- given
            acceptFirstResultAndMaxResults(fr -> mr -> {
                // ------------------------------------------------------------------------------------------------ when
                final var result = applyEntityManager(em -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(entityClass);
                    final var root = query.from(entityClass);
                    query.select(root);
                    query.where(builder.equal(
                            root.get(Character_.location_).get(Location_.id),
                            location_Id
                    ));
                    query.orderBy(builder.asc(root.get(Character_.id)));
                    final var typed = em.createQuery(query);
                    Optional.ofNullable(fr).ifPresent(typed::setFirstResult);
                    Optional.ofNullable(mr).ifPresent(typed::setMaxResults);
                    return typed.getResultList();
                });
                // ------------------------------------------------------------------------------------------------ then
                verify(location_Id, fr, mr, result);
            });
        }
    }

    @Nested
    class UsingEntity_Test {

        private static void verify(final Location location, final Integer firstResult, final Integer maxResults,
                                   final List<Character> entityList) {
            if (firstResult != null && entityList.isEmpty()) {
                return;
            }
            assertThat(entityList)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .hasSizeLessThanOrEqualTo(Optional.ofNullable(maxResults).orElse(Integer.MAX_VALUE))
                    .isSortedAccordingTo(Character.COMPARING_ID)
                    .extracting(Character::getLocation_)
                    .containsOnly(location)
            ;
        }

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _QueryLanguage_(final int location_Id) {
            acceptEntityManager(em -> {
                final var location_ = em.find(Location.class, location_Id);
                acceptFirstResultAndMaxResults(fr -> mr -> {
                    final var query = em.createQuery(
                            """
                                    SELECT c
                                    FROM Character c
                                    WHERE c.location_ = :location_
                                    ORDER BY c.id ASC""",
                            entityClass
                    );
                    query.setParameter(Character_.LOCATION_, location_);
                    Optional.ofNullable(fr).ifPresent(query::setFirstResult);
                    Optional.ofNullable(mr).ifPresent(query::setMaxResults);
                    final var resultList = query.getResultList();
                    verify(location_, fr, mr, resultList);
                });
            });
        }

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _CriteriaApi_(final int location_Id) {
            acceptEntityManager(em -> {
                final var location_ = em.find(Location.class, location_Id);
                acceptFirstResultAndMaxResults(fr -> mr -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(Character.class);
                    final var root = query.from(Character.class);
                    query.select(root);
                    query.where(builder.equal(root.get(Character_.location_), location_));
                    query.orderBy(builder.asc(root.get(Character_.id)));
                    final var typed = em.createQuery(query);
                    Optional.ofNullable(fr).ifPresent(typed::setFirstResult);
                    Optional.ofNullable(mr).ifPresent(typed::setMaxResults);
                    final var resultList = typed.getResultList();
                    verify(location_, fr, mr, resultList);
                });
            });
        }
    }
}
