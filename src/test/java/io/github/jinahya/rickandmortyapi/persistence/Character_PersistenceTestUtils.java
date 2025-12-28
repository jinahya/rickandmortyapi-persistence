package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SuppressWarnings({
        "java:S117" // Local variable and method parameter names should comply with a naming convention
})
final class Character_PersistenceTestUtils {

    static List<Character> getAllCharacters(final EntityManager entityManager) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return entityManager
                    .createQuery("""
                                         SELECT c
                                         FROM Character c
                                         ORDER BY c.id ASC""",
                                 Character.class
                    )
                    .getResultList();
        }
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(Character.class);
        final var root = query.from(Character.class);
        query.select(root);
        query.orderBy(builder.asc(root.get(Character_.id)));
        return entityManager.createQuery(query).getResultList();
    }

    static List<Character> getAllCharacters(final EntityManager entityManager, final Collection<Integer> characterIds) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return entityManager
                    .createQuery("""
                                         SELECT c
                                         FROM Character c
                                         WHERE c.id IN :characterIds
                                         ORDER BY c.id ASC""",
                                 Character.class
                    )
                    .setParameter("characterIds", characterIds)
                    .getResultList();
        }
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(Character.class);
        final var root = query.from(Character.class);
        query.select(root);
        query.where(root.get(Character_.id).in(characterIds));
        query.orderBy(builder.asc(root.get(Character_.id)));
        return entityManager.createQuery(query).getResultList();
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private Character_PersistenceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
