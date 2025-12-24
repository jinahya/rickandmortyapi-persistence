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
import java.util.Optional;
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
    void selectAll__(final EntityManager entityManager, final List<Character> entityList) {
        super.selectAll__(entityManager, entityList);
        assertThat(entityList)
                .as("all characters")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_CHARACTERS);
        {
            final var defined = EnumSet.allOf(Character_Status.class);
            final var selected = entityList.stream()
                                           .map(Character::getStatus)
                                           .distinct()
                                           .collect(Collectors.toCollection(
                                                   () -> EnumSet.noneOf(Character_Status.class)));
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Species.class);
            final var selected = entityList.stream()
                                           .map(Character::getSpecies)
                                           .distinct()
                                           .collect(Collectors.toCollection(
                                                   () -> EnumSet.noneOf(Character_Species.class)));
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Type.class);
            final var selected = entityList.stream()
                                           .map(Character::getType).filter(Objects::nonNull)
                                           .distinct()
                                           .collect(
                                                   Collectors.toCollection(() -> EnumSet.noneOf(Character_Type.class)));
            assertThat(selected).isEqualTo(defined);
        }
        {
            final var defined = EnumSet.allOf(Character_Gender.class);
            final var selected = entityList.stream()
                                           .map(Character::getGender)
                                           .distinct()
                                           .collect(Collectors.toCollection(
                                                   () -> EnumSet.noneOf(Character_Gender.class)));
            assertThat(selected).isEqualTo(defined);
        }
        entityList.forEach(character -> {
            Optional.ofNullable(character.getOrigin()).ifPresent(origin -> {
                assertThat(character.getOrigin_()).isNotNull().satisfies(origin_ -> {
                    assertThat(origin_.getName()).isEqualTo(origin.getName());
                    assertThat(origin_.getUrl()).isEqualTo(origin.getUrl());
                })
                ;
            });
            Optional.ofNullable(character.getOrigin_()).ifPresent(origin_ -> {
                assertThat(character.getOrigin()).isNotNull().satisfies(origin -> {
                    assertThat(origin.getName()).isEqualTo(origin_.getName());
                    assertThat(origin.getUrl()).isEqualTo(origin_.getUrl());
                })
                ;
            });
        });
        entityList.forEach(character -> {
            Optional.ofNullable(character.getLocation()).ifPresent(location -> {
                assertThat(character.getLocation_()).isNotNull().satisfies(location_ -> {
                    assertThat(location_.getName()).isEqualTo(location.getName());
                    assertThat(location_.getUrl()).isEqualTo(location.getUrl());
                })
                ;
            });
            Optional.ofNullable(character.getLocation_()).ifPresent(location_ -> {
                assertThat(character.getLocation()).isNotNull().satisfies(location -> {
                    assertThat(location.getName()).isEqualTo(location_.getName());
                    assertThat(location.getUrl()).isEqualTo(location_.getUrl());
                })
                ;
            });
        });
    }

    @DisplayName("both origin_name and origin_url columns are null -> origin(_) attribute is null")
    @ValueSource(ints = {
            // SELECT id
            // FROM character
            // WHERE origin_name IS NULL AND origin_url IS NULL
            2, 8, 10, 13, 14
    })
    @ParameterizedTest
    void _originIsNull_BothOriginNameAndOriginUrlAreNull(final int id) {
        applyEntityManager(em -> {
            final var found = em.find(entityClass, id);
            assertThat(found)
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getOrigin()).isNull();
                        assertThat(v.getOrigin_()).isNull();
                    })
            ;
            return null;
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
        applyEntityManager(em -> {
            final var found = em.find(entityClass, id);
            assertThat(found)
                    .isNotNull()
                    .satisfies(v -> {
                        assertThat(v.getLocation()).isNull();
                        assertThat(v.getLocation_()).isNull();
                    })
            ;
            return null;
        });
    }
}
