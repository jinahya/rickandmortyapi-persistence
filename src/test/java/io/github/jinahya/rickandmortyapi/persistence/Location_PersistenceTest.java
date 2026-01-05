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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Location_PersistenceTest
        extends _BaseEntity_PersistenceTest<Location, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Location_PersistenceTest() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final Root<Location> root) {
        super.selectAll__(root);
        final Fetch<Location, Character> residents_Fetch = root.fetch(Location_.residents_, JoinType.LEFT);
        final Fetch<Character, Location> origin_Fetch = residents_Fetch.fetch(Character_.origin_, JoinType.LEFT);
        final Fetch<Character, Location> location_Fetch = residents_Fetch.fetch(Character_.location_, JoinType.LEFT);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<Location> entityList) {
        super.selectAll__(entityManager, entityList);
        assertThat(entityList)
                .as("all locations")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_LOCATIONS);
        {
            // fetch location.originCharacters_
            final var locationIds = entityList.stream().map(Location::getId).distinct().toList();
            entityManager.createQuery(
                            """
                                    SELECT l
                                    FROM Location l
                                    LEFT JOIN FETCH l.originCharacters_
                                    WHERE l.id IN :locationIds""",
                            Location.class
                    )
                    .setParameter("locationIds", locationIds)
                    .getResultList();
        }
        {
            // fetch location.locationCharacters_
            // and assert that location.residents_ is equal to location.locationCharacters_
            final var locationIds = entityList.stream().map(Location::getId).distinct().toList();
            entityManager.createQuery(
                            """
                                    SELECT l
                                    FROM Location l
                                    LEFT JOIN FETCH l.locationCharacters_
                                    WHERE l.id IN :locationIds""",
                            Location.class
                    )
                    .setParameter("locationIds", locationIds)
                    .getResultList();
            entityList.forEach(l -> {
                assertThat(l.getResidents_()).hasSameElementsAs(l.getLocationCharacters_());
                // both attributes are annotated with @OrderBy("id ASC")
                assertThat(l.getResidents_()).containsExactlyElementsOf(l.getLocationCharacters_());
            });
        }
        {
            final var selected = entityList.stream()
                    .map(Location::getType)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            final var defined = EnumSet.allOf(Location_Type.class);
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        {
            final var selected = entityList.stream()
                    .map(Location::getDimension)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            final var defined = EnumSet.allOf(Location_Dimension.class);
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        entityList.forEach(e -> {
            assertThat(e.getResidents_())
                    .as("location(%d).residents_", e.getId())
                    .satisfiesAnyOf(
                            l -> assertThat(l).isEmpty(),
                            l -> assertThat(l).isNotEmpty()
                                    .doesNotContainNull()
                                    .doesNotHaveDuplicates()
                    );
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class Distributions_Test {

        static void __(final EntityManager em, final String attribute) {
            final List<Object[]> results;
            if (ThreadLocalRandom.current().nextBoolean()) {
                results = em.createQuery(
                        """
                                SELECT l.%s,
                                       COUNT(l) AS count
                                FROM Location l
                                GROUP BY 1
                                ORDER BY count DESC""".formatted(attribute),
                        Object[].class
                ).getResultList();
            } else {
                final var builder = em.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(Location.class);
                final var count = builder.count(root);
                query.select(builder.array(root.get(attribute), count));
                query.groupBy(root.get(attribute));
                query.orderBy(builder.desc(count));
                results = em.createQuery(query).getResultList();
            }
            for (final var result : results) {
                final var value = result[0];
                final var count = (Long) result[1];
                log.info("{}: {}", String.format("%1$40s", value), String.format("%1$3d", count));
            }
        }

        @Test
        void type() {
            applyEntityManager(em -> {
                __(em, Location_.TYPE);
                return null;
            });
        }

        @Test
        void dimension() {
            applyEntityManager(em -> {
                __(em, Location_.DIMENSION);
                return null;
            });
        }
    }
}
