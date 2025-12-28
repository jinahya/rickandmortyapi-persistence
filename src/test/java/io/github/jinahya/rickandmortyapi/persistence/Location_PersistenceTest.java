package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Location_PersistenceTest extends _BaseEntity_PersistenceTest<Location, Integer> {

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
            });
        }
        {
            final var defined = EnumSet.allOf(Location_Type.class);
            final var selected = entityList.stream()
                    .map(Location::getType)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            assertThat(selected).containsExactlyInAnyOrderElementsOf(defined);
        }
        {
            final var defined = EnumSet.allOf(Location_Dimension.class);
            final var selected = entityList.stream()
                    .map(Location::getDimension)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
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
}
