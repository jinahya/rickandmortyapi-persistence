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

import io.github.jinahya.rickandmortyapi.persistence.mapped.Character_Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

// 재한님
@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
class Character_FindAllByType_PersistenceTest
        extends _BaseEntity_Persistence_<Character, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_FindAllByType_PersistenceTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private <R> R applyFirstResultAndMaxResults(
            final Function<? super Integer, ? extends Function<? super Integer, ? extends R>> function) {
        final var firstResult = ThreadLocalRandom.current().nextBoolean()
                ? null
                : ThreadLocalRandom.current().nextInt(1, 5);
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

    private void verify(final Character_Type type, final Integer firstResult, final Integer maxResults,
                        final List<Character> results) {
        if (firstResult != null && results.isEmpty()) {
            return;
        }
        assertThat(results)
                .isNotNull()
                .doesNotContainNull()
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Character.COMPARING_ID)
                .extracting(Character::getType)
                .filteredOn(Objects::nonNull)
                .containsOnly(type)
        ;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @EnumSource(Character_Type.class)
    @ParameterizedTest
    void _QueryLanguage_(final Character_Type type) {
        acceptEntityManager(em -> {
            acceptFirstResultAndMaxResults(fr -> mr -> {
                final var query = em.createQuery(
                        """
                                SELECT c
                                FROM Character c
                                WHERE c.type = :type
                                ORDER BY c.id ASC""",
                        entityClass
                );
                query.setParameter("type", type);
                Optional.ofNullable(fr).ifPresent(query::setFirstResult);
                Optional.ofNullable(mr).ifPresent(query::setMaxResults);
                final var result = query.getResultList();
                verify(type, fr, mr, result);
            });
        });
    }

    @EnumSource(Character_Type.class)
    @ParameterizedTest
    void _CriteriaApi_(final Character_Type type) {
        acceptFirstResultAndMaxResults(fr -> mr -> {
            acceptEntityManager(em -> {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Character.class);
                final var root = query.from(Character.class);
                query.where(builder.equal(root.get(Character_.type), type));
                query.orderBy(builder.asc(root.get(Character_.id)));
                final var typed = em.createQuery(query);
                Optional.ofNullable(fr).ifPresent(typed::setFirstResult);
                Optional.ofNullable(mr).ifPresent(typed::setMaxResults);
                final var result = typed.getResultList();
                verify(type, fr, mr, result);
            });
        });
    }
}
