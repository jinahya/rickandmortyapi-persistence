package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

final class __JakartaPersistence_TestUtils {

    private static final Map<Class<?>, String> ENTITY_NAMES = new ConcurrentHashMap<>();

    static <T> String getEntityName(final EntityManager entityManager, final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        return ENTITY_NAMES.computeIfAbsent(
                entityClass,
                k -> entityManager.getMetamodel().entity(k).getName()
        );
    }

    static <R> R applyEntityManager(final EntityManagerFactory entityManagerFactory,
                                    final Function<? super EntityManager, ? extends R> mapper) {
        Objects.requireNonNull(entityManagerFactory, "entityManagerFactory is null");
        Objects.requireNonNull(mapper, "mapper is null");
        try (final EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return mapper.apply(entityManager);
        }
    }

    static void acceptEntityManager(final EntityManagerFactory entityManagerFactory,
                                    final Consumer<? super EntityManager> consumer) {
        Objects.requireNonNull(consumer, "mapper is null");
        applyEntityManager(entityManagerFactory, em -> {
            consumer.accept(em);
            return null;
        });
    }

    static <T, R> R selectAll(
            final EntityManager entityManager, final Class<T> entityClass, final Consumer<? super Root<T>> rootConsumer,
            final Function<? super List<T>, ? extends R> resultMapper) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(resultMapper, "resultMapper is null");
        final List<T> entityList;
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> query = builder.createQuery(entityClass);
        final Root<T> root = query.from(entityClass);
        rootConsumer.accept(root);
        query.select(root).distinct(true);
        entityList = entityManager.createQuery(query).getResultList();
        return resultMapper.apply(entityList);
    }

    static <T> List<T> selectAll(final EntityManager entityManager, final Class<T> entityClass,
                                 final Consumer<? super Root<T>> rootConsumer) {
        return selectAll(entityManager, entityClass, rootConsumer, Function.identity());
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    private __JakartaPersistence_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
