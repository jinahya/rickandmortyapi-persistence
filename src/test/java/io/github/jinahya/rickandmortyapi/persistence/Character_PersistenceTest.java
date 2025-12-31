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
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.ObjLongConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_PersistenceTest
        extends _BaseEntity_PersistenceTest<Character, Integer> {

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
    Character_PersistenceTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final Root<Character> root) {
        super.selectAll__(root);
        final Fetch<Character, Location> origin__Fetch = root.fetch(Character_.origin_, JoinType.LEFT);
        final Fetch<Character, Location> location__Fetch = root.fetch(Character_.location_, JoinType.LEFT);
        final Fetch<Character, Episode> episodes__Fetch = root.fetch(Character_.episodes_, JoinType.LEFT);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<Character> entityList) {
        super.selectAll__(entityManager, entityList);
        assertThat(entityList)
                .as("all characters")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_CHARACTERS);
        {
            final var defined = EnumSet.allOf(Character_Status.class);
            final var selected = entityList.stream()
                    .map(Character::getStatus)
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character_Status.class)));
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Species.class);
            final var selected = entityList.stream()
                    .map(Character::getSpecies)
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character_Species.class)));
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Type.class);
            final var selected = entityList.stream()
                    .map(Character::getType)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character_Type.class)));
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Gender.class);
            final var selected = entityList.stream()
                    .map(Character::getGender)
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character_Gender.class)));
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        entityList.forEach(c -> {
            // origin != null ? -> origin_ != null && name/url equals
            Optional.ofNullable(c.getOrigin()).ifPresent(o -> {
                assertThat(c.getOrigin_()).isNotNull().satisfies(o_ -> {
                    assertThat(o_.getName()).isEqualTo(o.getName());
                    assertThat(o_.getUrl()).isEqualTo(o.getUrl());
                });
            });
            // origin_ != null ? -> origin != null && name/url equals
            Optional.ofNullable(c.getOrigin_()).ifPresent(o_ -> {
                assertThat(c.getOrigin()).isNotNull().satisfies(o -> {
                    assertThat(o.getName()).isEqualTo(o_.getName());
                    assertThat(o.getUrl()).isEqualTo(o_.getUrl());
                });
            });
            // location != null ? -> location_ != null && name/url equals
            Optional.ofNullable(c.getLocation()).ifPresent(l -> {
                assertThat(c.getLocation_()).isNotNull().satisfies(l_ -> {
                    assertThat(l_.getName()).isEqualTo(l.getName());
                    assertThat(l_.getUrl()).isEqualTo(l.getUrl());
                });
            });
            // location_ != null ? -> location != null && name/url equals
            Optional.ofNullable(c.getLocation_()).ifPresent(l_ -> {
                assertThat(c.getLocation()).isNotNull().satisfies(l -> {
                    assertThat(l.getName()).isEqualTo(l_.getName());
                    assertThat(l.getUrl()).isEqualTo(l_.getUrl());
                });
            });
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("both origin_name and origin_url columns are null -> origin(_) attribute is null")
    @ValueSource(ints = {
            // SELECT id
            // FROM character
            // WHERE origin_name IS NULL AND origin_url IS NULL
            2, 8, 10, 13, 14
    })
    @ParameterizedTest
    void _originIsNull_BothOriginNameAndOriginUrlAreNull(final int id) {
        acceptEntityManager(em -> {
            final var found = em.find(entityClass, id);
            assertThat(found)
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getOrigin()).isNull();
                        assertThat(v.getOrigin_()).isNull();
                    });
        });
    }

    @DisplayName("both location_name and location_url columns are null -> location(_) attribute is null")
    @ValueSource(ints = {
            // SELECT id
            // FROM character
            // WHERE location_name IS NULL AND location_url IS NULL
            19, 30, 36, 46, 57
    })
    @ParameterizedTest
    void _locationIsNull_BothLocationNameAndLocationUrlAreNull(final int id) {
        acceptEntityManager(em -> {
            final var found = em.find(entityClass, id);
            assertThat(found)
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getLocation()).isNull();
                        assertThat(v.getLocation_()).isNull();
                    });
        });
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
