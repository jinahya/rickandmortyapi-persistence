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
import jakarta.persistence.EntityManagerFactory;
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
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(entityClass);
        final var root = query.from(entityClass);
        rootConsumer.accept(root);
        query.select(root).distinct(true);
        final var entityList = entityManager.createQuery(query).getResultList();
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
