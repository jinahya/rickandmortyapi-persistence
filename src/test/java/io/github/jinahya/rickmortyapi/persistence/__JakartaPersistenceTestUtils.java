package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

final class __JakartaPersistenceTestUtils {

    private static final Map<Class<?>, String> ENTITY_NAMES = new ConcurrentHashMap<>();

    static <T> String getEntityName(final EntityManager entityManager, final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        return ENTITY_NAMES.computeIfAbsent(
                entityClass,
                k -> entityManager.getMetamodel().entity(k).getName()
        );
    }

    static <T, R> R selectAll(final EntityManager entityManager, final Class<T> entityClass,
                              final Function<? super List<T>, ? extends R> mapper) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(mapper, "mapper is null");
        final List<T> entityList;
        if (ThreadLocalRandom.current().nextBoolean()) {
            final String entityName = getEntityName(entityManager, entityClass);
            final TypedQuery<T> query = entityManager.createQuery(
                    "SELECT e FROM %s e".formatted(entityName),
                    entityClass
            );
            entityList = query.getResultList();
        } else {
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(entityClass);
            final Root<T> root = query.from(entityClass);
            query.select(root);
            entityList = entityManager.createQuery(query).getResultList();
        }
        return mapper.apply(entityList);
    }

    static <T> List<T> selectAll(final EntityManager entityManager, final Class<T> entityClass) {
        return selectAll(entityManager, entityClass, Function.identity());
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private __JakartaPersistenceTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
