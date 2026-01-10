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

import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Species;
import io.github.jinahya.rickandmortyapi.persistence.mapped.__ColumnEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

// 재한님
@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_FindAllBySpecies_PersistenceTest
        extends _BaseEntity_Persistence_<Character, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_FindAllBySpecies_PersistenceTest() {
        super(Character.class, Integer.class);
    }

    @Nested
    class UsingEnumValues_Test {

        // SELECT c
        // FROM Character c
        // WHERE c.species = :species

        @EnumSource(Character_Species.class)
        @ParameterizedTest
        void _QueryLanguage_(final Character_Species species) {
            final var result = applyEntityManager(em -> {
                return null;
            });
        }

        @EnumSource(Character_Species.class)
        @ParameterizedTest
        void _CriteriaApi_(final Character_Species species) {
            final var result = applyEntityManager(em -> {
                return null;
            });
        }
    }

    @Nested
    class UsingColumnValues_Test {

        private static Stream<String> speciesColumnValueStream() {
            return Arrays.stream(Character_Species.values())
                    .map(__ColumnEnum::columnValue);
        }

        @MethodSource({
                "speciesColumnValueStream"
        })
        @ParameterizedTest
        void _QueryLanguage_(final String speciesColumnValue) {
            final var result = applyEntityManager(em -> {
                return null;
            });
        }

        @MethodSource({
                "speciesColumnValueStream"
        })
        @ParameterizedTest
        void _CriteriaApi_(final String speciesColumnValue) {
            final var result = applyEntityManager(em -> {
                return null;
            });
        }
    }
}
