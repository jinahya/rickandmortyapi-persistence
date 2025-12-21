package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.List;

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
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_CHARACTERS);
        {
            final var defined = EnumSet.allOf(Character.Status.class);
            final var selected = characters.stream().map(Character::getStatus).distinct().toList();
            assertThat(selected).hasSameElementsAs(defined);
        }
        {
            final var defined = EnumSet.allOf(Character.Species.class);
            final var selected = characters.stream().map(Character::getSpecies).distinct().toList();
            assertThat(selected).hasSameElementsAs(defined);
        }
        {
            final var defined = EnumSet.allOf(Character.Gender.class);
            final var selected = characters.stream().map(Character::getGender).distinct().toList();
            assertThat(selected).hasSameElementsAs(defined);
        }
    }
}
