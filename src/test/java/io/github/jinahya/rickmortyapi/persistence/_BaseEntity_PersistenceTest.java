package io.github.jinahya.rickmortyapi.persistence;

import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@AddBeanClasses({
        __PersistenceUnitProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_PersistenceTest<ENTITY extends _BaseEntity, ID> extends __BaseEntity__<ENTITY, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    _BaseEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll__() {
        applyEntityManager(em -> {
            final List<ENTITY> entityList;
            if (ThreadLocalRandom.current().nextBoolean()) {
                final TypedQuery<ENTITY> query = em.createQuery(
                        "SELECT e FROM %s e".formatted(getEntityName()),
                        entityClass
                );
                entityList = query.getResultList();
            } else {
                final CriteriaBuilder builder = em.getCriteriaBuilder();
                final CriteriaQuery<ENTITY> query = builder.createQuery(entityClass);
                final Root<ENTITY> root = query.from(entityClass);
                query.select(root);
                entityList = em.createQuery(query).getResultList();
            }
            selectAll__(em, entityList);
            return entityList;
        });
    }

    void selectAll__(final EntityManager entityManager, final List<ENTITY> entityList) {
    }

    // --------------------------------------------------------------------------------------------------- entityManager

    /**
     * Returns the result of the specified functino applied to an entity manager.
     *
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    final <R> R applyEntityManager(final @Nonnull Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (final var entityManager = entityManagerFactory.createEntityManager()) {
            return function.apply(entityManager);
        }
    }

    // ------------------------------------------------------------------------------------------------------ entityName
    final String getEntityName() {
        var result = entityName;
        if (result == null) {
            entityName = result = entityManagerFactory.getMetamodel().entity(entityClass).getName();
        }
        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private EntityManagerFactory entityManagerFactory;

    private volatile String entityName;
}
