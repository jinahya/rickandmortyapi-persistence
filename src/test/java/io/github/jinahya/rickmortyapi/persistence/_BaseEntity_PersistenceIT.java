package io.github.jinahya.rickmortyapi.persistence;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_PersistenceIT<ENTITY extends _BaseEntity, ID> extends _BaseEntity_Persistence_<ENTITY, ID> {

    _BaseEntity_PersistenceIT(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void selectAll__() {
        final var selected = applyEntityManager(em -> {
            final var builder = em.getCriteriaBuilder();
            final var query = builder.createQuery(entityClass);
            final var root = query.from(entityClass);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
        assertThat(selected).isNotEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__PersistenceProducer.__itPU
    @Inject
    private EntityManagerFactory entityManagerFactory;
}
