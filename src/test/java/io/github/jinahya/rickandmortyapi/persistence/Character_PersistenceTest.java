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

import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Gender;
import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Species;
import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Status;
import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
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


    @Nested
    class FindBySpecies_Test {

        @Test
        void _QueryLanguage_() {
        }

        @Test
        void _CriteriaApi_() {
        }
    }

    @Nested
    class FindByOrigin_Test {
    }
}
