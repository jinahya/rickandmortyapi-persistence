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

        // SELECT c
        // FROM Character c
        // WHERE c.origin_.id = :origin_id

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _QueryLanguage_(final int origin_id) {
        }

        @ValueSource(ints = {
                1, 2, 3
        })
        @ParameterizedTest
        void _CriteriaApi_(final int origin_id) {
        }
    }

    @Nested
    class UsingEntity_Test {

        // SELECT c
        // FROM Character c
        // WHERE c.origin_ = :origin_

        @Test
        void _QueryLanguage_() {
            final var result = applyEntityManager(em -> {
                final var origin_ = em.find(Location.class, 1);
                return null;
            });
        }

        @Test
        void _CriteriaApi_() {
            final var result = applyEntityManager(em -> {
                final var origin_ = em.find(Location.class, 1);
                return null;
            });
        }
    }
}
