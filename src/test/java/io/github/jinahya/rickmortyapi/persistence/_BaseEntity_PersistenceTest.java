package io.github.jinahya.rickmortyapi.persistence;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;

abstract class _BaseEntity_PersistenceTest<ENTITY extends _BaseEntity, ID>
        extends _BaseEntity_Persistence_<ENTITY, ID> {

    _BaseEntity_PersistenceTest(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @__PersistenceProducer.__testPU
    @Inject
    private EntityManagerFactory entityManagerFactory;
}
