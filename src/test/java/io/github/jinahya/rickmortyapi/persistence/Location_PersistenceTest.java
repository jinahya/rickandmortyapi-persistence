package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Fetch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
class Location_PersistenceTest extends _BaseEntity_PersistenceTest<Location, Integer> {

    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    @SuperBuilder
    static class FetchAllParameters {

        boolean fetchResidents_;

        @Nullable
        @Size(min = 1)
        List<@Positive @NotNull Integer> ids;

        Character_PersistenceTest.FetchAllParameters residents_FetchAllParameters;
    }

    static List<Location> fetchAll(@Nonnull @NotNull final EntityManager entityManager,
                                   @Nonnull @Valid @NotNull final FetchAllParameters parameters) {
        final List<Location> locations;
        {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Location.class);
            final var root = query.from(Location.class);
            query.select(root).distinct(true);
            if (parameters.fetchResidents_) {
                final Fetch<Location, Character> residents_Fetch = root.fetch(Location_.residents_);
            }
            if (parameters.ids != null && !parameters.ids.isEmpty()) {
                query.where(root.get(Location_.id).in(parameters.ids));
            }
            query.orderBy(builder.asc(root.get(Location_.id)));
            locations = entityManager.createQuery(query).getResultList();
        }
        if (parameters.fetchResidents_) {
            Optional.ofNullable(parameters.residents_FetchAllParameters).ifPresent(v -> {
                final var ids = locations.stream()
                        .flatMap(l -> l.getResidents_().stream())
                        .map(Character::getId)
                        .distinct()
                        .toList();
                v.fetchOrigin_ = false;
                v.fetchLocation_ = false;
                v.ids = ids;
                final var residents = Character_PersistenceTest.fetchAll(entityManager, v);
            });
        }
        return locations;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    Location_PersistenceTest() {
        super(Location.class, Integer.class);
    }
}
