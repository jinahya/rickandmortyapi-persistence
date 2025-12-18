package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Character_PersistenceTest extends _BaseEntity_PersistenceTest<Character, Integer> {

    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @SuperBuilder
    static class FetchAllParameters {

        boolean fetchOrigin_;

        boolean fetchLocation_;

        @Nullable
        @Size(min = 1)
        List<@Positive @NotNull Integer> ids;

        @Nullable
        final Location_PersistenceTest.FetchAllParameters origin_FetchAllParameters;

        @Nullable
        final Location_PersistenceTest.FetchAllParameters location_FetchAllParameters;

//        @Nullable
//        final Episode_PersistenceIT.FetchAllParameters episodes_FetchAllParameters;
    }

    static List<Character> fetchAll(@Nonnull @NotNull final EntityManager entityManager,
                                    final FetchAllParameters parameters) {
        final List<Character> characters;
        {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Character.class);
            final var root = query.from(Character.class);
            query.select(root).distinct(true);
            final Fetch<Character, Location> origin_Fetch = root.fetch(Character_.origin_);
            final Fetch<Character, Location> location_Fetch = root.fetch(Character_.location_);
            final Fetch<Character, Episode> episodes_Fetch = root.fetch(Character_.episodes_);
            if (parameters.ids != null && !parameters.ids.isEmpty()) {
                query.where(root.get(Character_.id).in(parameters.ids));
            }
            query.orderBy(builder.asc(root.get(Character_.id)));
            characters = entityManager.createQuery(query).getResultList();
        }
        Optional.ofNullable(parameters.origin_FetchAllParameters).ifPresent(v -> {
            final var ids = characters.stream()
                    .map(Character::getOrigin_)
                    .filter(Objects::nonNull)
                    .map(Location::getId).distinct()
                    .toList();
            if (ids.isEmpty()) {
                return;
            }
            v.fetchResidents_ = false;
            v.ids = ids;
            Location_PersistenceTest.fetchAll(entityManager, v);
        });
        Optional.ofNullable(parameters.location_FetchAllParameters).ifPresent(v -> {
            final var ids = characters.stream()
                    .map(Character::getLocation_)
                    .filter(Objects::nonNull)
                    .map(Location::getId).distinct()
                    .toList();
            if (ids.isEmpty()) {
                return;
            }
            v.fetchResidents_ = false;
            v.ids = ids;
            Location_PersistenceTest.fetchAll(entityManager, v);
        });
        return characters;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Character_PersistenceTest() {
        super(Character.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    void selectAll__(final EntityManager entityManager, final List<Character> characters) {
        super.selectAll__(entityManager, characters);
        assertThat(characters)
                .as("all characters")
                .hasSize(_PersistenceConstants.NUMBER_OF_ALL_CHARACTERS);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class FetchAll_IT {

        @Test
        void queryLanguage__() {
            final var result = applyEntityManager(em -> {
//                return fetchAll(em, true, true, true, true, true, null);
                return null;
            });
        }

        @Test
        void criteriaApi__() {
        }
    }
}
