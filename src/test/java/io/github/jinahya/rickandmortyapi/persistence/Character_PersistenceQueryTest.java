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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.ObjLongConsumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_PersistenceQueryTest
        extends _BaseEntity_Persistence_<Character, Integer> {

    private static <R> R applyFirstResultAndMaxResults(final IntFunction<? extends IntFunction<? extends R>> function) {
        Objects.requireNonNull(function, "function is null");
        final var firstResult = ThreadLocalRandom.current().nextInt(0, _PersistenceConstants.NUMBER_OF_ALL_CHARACTERS);
        final var maxResults = ThreadLocalRandom.current().nextInt(1, 100);
        return function.apply(firstResult).apply(maxResults);
    }

    private static Stream<Arguments> getFirstResultAndMaxResultsArgumentsStream() {
        return applyFirstResultAndMaxResults(fr -> mr -> Stream.of(Arguments.of(fr, mr)));
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_PersistenceQueryTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("SelectList__OrderByIdAsc")
    @Nested
    class SelectList__OrderByIdAsc_Test {

        private static Stream<Arguments> arguments() {
            return getFirstResultAndMaxResultsArgumentsStream();
        }

        private static void verify(final int firstResult, final int maxResults, final List<Character> entityList) {
            assertThat(entityList)
                    .isNotNull()
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .hasSizeLessThanOrEqualTo(maxResults)
                    .isSortedAccordingTo(Character.COMPARING_ID)
                    .satisfies(l -> {
                        assertThat(l.getFirst().getId()).isEqualTo(firstResult + 1);
                    })
                    .extracting(Character::getId)
                    .isSorted()
            ;
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] firstResult: {0}, maxResults: {1}")
        void namedQuery__(final int firstResult, final int maxResults) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Character.SelectList__OrderByIdAsc", entityClass);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(firstResult, maxResults, entityList);
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] firstResult: {0}, maxResults: {1}")
        void queryLanguage__(final int firstResult, final int maxResults) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createQuery("SELECT c FROM Character c ORDER BY c.id ASC", entityClass);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(firstResult, maxResults, entityList);
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] firstResult: {0}, maxResults: {1}")
        void criteriaApi__(final int firstResult, final int maxResults) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.orderBy(builder.asc(root.get(Character_.id)));
                return em.createQuery(query)
                        .setFirstResult(firstResult)
                        .setMaxResults(maxResults)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(firstResult, maxResults, entityList);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("SelectList_NameEqual_OrderByIdAsc")
    @Nested
    class SelectList_NameEqual_OrderByIdAsc_Test {

        private static Stream<String> arguments() {
            return Stream.of(
                    "Rick Sanchez",
                    "Morty Smith"
            );
        }

        private static void verify(final String name, final List<Character> entityList) {
            assertThat(entityList)
                    .isNotNull()
                    .isNotEmpty()
                    .doesNotContainNull()
                    .doesNotHaveDuplicates()
                    .isSortedAccordingTo(Character.COMPARING_ID)
                    .extracting(Character::getName)
                    .containsOnly(name)
            ;
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] name: {0}")
        void namedQuery__(final String name) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createNamedQuery("Character.SelectList_NameEqual_OrderByIdAsc", entityClass);
                query.setParameter("name", name);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(name, entityList);
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] name: {0}")
        void queryLanguage__(final String name) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var query = em.createQuery(
                        """
                                SELECT c
                                FROM Character c
                                WHERE c.name = :name
                                ORDER BY c.id ASC""",
                        entityClass
                );
                query.setParameter("name", name);
                return query.getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(name, entityList);
        }

        @MethodSource({"arguments"})
        @ParameterizedTest(name = "[{index}] name: {0}")
        void criteriaApi__(final String name) {
            // ---------------------------------------------------------------------------------------------------- when
            final var entityList = applyEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                query.where(builder.equal(root.get(Character_.name), name));
                query.orderBy(builder.asc(root.get(Character_.id)));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            verify(name, entityList);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ValueSource(strings = {
            Character_.STATUS,
            Character_.SPECIES,
            Character_.TYPE,
            Character_.GENDER
    })
    @ParameterizedTest
    void distribution__(final String attribute) {
        final ObjLongConsumer<Object> printer = (v, c) -> {
            log.info("{}: {}", String.format("%1$40s", v), String.format("%1$3d", c));
        };
        acceptEntityManager(em -> {
            if (ThreadLocalRandom.current().nextBoolean()) {
                final var query =
                        """
                                SELECT c.%1$s,
                                       COUNT(c) AS count
                                FROM Character c
                                GROUP BY c.%1$s
                                ORDER BY count DESC""".formatted(attribute);
                final var results = em.createQuery(query, Object[].class).getResultList();
                results.forEach(r -> printer.accept(r[0], (Long) r[1]));
            } else {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createTupleQuery();
                final var root = query.from(Character.class);
                final var count = builder.count(root);
                query.select(builder.tuple(root.get(attribute), count));
                query.groupBy(root.get(attribute));
                query.orderBy(builder.desc(count));
                final var results = em.createQuery(query).getResultList();
                results.forEach(r -> printer.accept(
                        r.get(0, Object.class),
                        r.get(1, Long.class)
                ));
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Find characters who appeared in all specified episodes")
    @Nested
    class FindCharactersWhoAppearedInAllSpecifiedEpisode_Test {

        private static Stream<Arguments> episodeIdsAndCharacterIds() {
            return Stream.of(
                    Arguments.of(
                            List.of(1, 2, 3),
                            List.of(1, 2, 38, 175, 338)
                    ),
                    Arguments.of(
                            List.of(10, 22, 51),
                            List.of(1, 2, 3, 4, 5, 215, 274, 294, 389))
            );
        }

        @MethodSource({"episodeIdsAndCharacterIds"})
        @ParameterizedTest
        void QueryLanguage__(final Collection<Integer> episodeIds, final Collection<Integer> characterIds) {
            // ---------------------------------------------------------------------------------------------------- when
            final var characterList = applyEntityManager(em -> {
                final var episodeList = Episode_PersistenceTestUtils.getEpisodesIdIn(em, episodeIds);
                final var episodeSize = episodeList.size();
                return em.createQuery(
                                """
                                        SELECT c
                                        FROM Character c JOIN c.episodes_ ce
                                        WHERE ce IN (:episodeList)
                                        GROUP BY c
                                        HAVING COUNT(DISTINCT ce) = :episodeSize
                                        ORDER BY c.id ASC
                                        """,
                                entityClass
                        )
                        .setParameter("episodeList", episodeList)
                        .setParameter("episodeSize", episodeSize)
                        .getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(characterList)
                    .extracting(Character::getId)
                    .containsExactlyElementsOf(characterIds);
        }

        @MethodSource({"episodeIdsAndCharacterIds"})
        @ParameterizedTest
        void CriteriaApi__(final Collection<Integer> episodeIds, final Collection<Integer> characterIds) {
            // ---------------------------------------------------------------------------------------------------- when
            final List<Character> characterList = applyEntityManager(em -> {
                final var episodeList = Episode_PersistenceTestUtils.getEpisodesIdIn(em, episodeIds);
                final var episodeSize = episodeList.size();
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(entityClass);
                final var root = query.from(entityClass);
                query.select(root);
                final var episodes_ = root.join(Character_.episodes_);
                query.where(episodes_.in(episodeList));
                query.groupBy(root);
                query.having(builder.equal(builder.countDistinct(episodes_), episodeSize));
                query.orderBy(builder.asc(root.get(Character_.id)));
                return em.createQuery(query).getResultList();
            });
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(characterList)
                    .extracting(Character::getId)
                    .containsExactlyElementsOf(characterIds);
        }
    }
}
