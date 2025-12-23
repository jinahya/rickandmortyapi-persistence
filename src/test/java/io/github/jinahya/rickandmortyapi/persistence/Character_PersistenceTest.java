package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Character_PersistenceTest extends _BaseEntity_PersistenceTest<Character, Integer> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_PersistenceTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    void selectAll__(final Root<Character> root) {
        super.selectAll__(root);
        final Fetch<Character, Location> origin_Fetch = root.fetch(Character_.origin_, JoinType.LEFT);
        final Fetch<Character, Location> location_Fetch = root.fetch(Character_.location_, JoinType.LEFT);
    }

    @Override
    void selectAll__(final EntityManager entityManager, final List<Character> characters) {
        super.selectAll__(entityManager, characters);
        assertThat(characters)
                .as("all characters")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_CHARACTERS)
        ;
        {
            final var defined = EnumSet.allOf(Character.Status.class);
            final var selected = characters.stream()
                    .map(Character::getStatus)
                    .distinct()
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character.Status.class)))
                    ;
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character.Species.class);
            final var selected = characters.stream()
                    .map(Character::getSpecies)
                    .distinct()
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character.Species.class)))
                    ;
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character.Type.class);
            final var selected = characters.stream()
                    .map(Character::getType).filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character.Type.class)))
                    ;
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character.Gender.class);
            final var selected = characters.stream()
                    .map(Character::getGender)
                    .distinct()
                    .collect(Collectors.toCollection(() -> EnumSet.noneOf(Character.Gender.class)))
                    ;
            assertThat(selected).isEqualTo(defined);
        }
    }

    @DisplayName("both origin_name and origin_url columns are null -> origin attribute is null")
    @ValueSource(ints = {
            // SELECT id
            // FROM character
            // WHERE origin_name IS NULL AND origin_url IS NULL
            2, 8, 10, 13, 14
    })
    @ParameterizedTest
    void _originIsNull_BothOriginNameAndOriginUrlAreNull(final int id) {
        // -------------------------------------------------------------------------------------------------------- when
        final var found = applyEntityManager(em -> em.find(entityClass, id));
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(found)
                .isNotNull()
                .satisfies(v -> {
                    assertThat(v.getOrigin()).isNull();
                })
        ;
    }

    @DisplayName("both location_name and location_url columns are null -> location attribute is null")
    @ValueSource(ints = {
            // SELECT id
            // FROM character
            // WHERE location_name IS NULL AND location_url IS NULL
            19, 30, 36, 46, 57
    })
    @ParameterizedTest
    void _locationIsNull_BothLocationNameAndLocationUrlAreNull(final int id) {
        // -------------------------------------------------------------------------------------------------------- when
        final var found = applyEntityManager(em -> em.find(entityClass, id));
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(found)
                .isNotNull()
                .satisfies(v -> {
                    assertThat(v.getLocation()).isNull();
                })
        ;
    }
}
