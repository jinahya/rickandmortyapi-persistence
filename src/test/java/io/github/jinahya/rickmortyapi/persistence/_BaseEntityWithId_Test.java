package io.github.jinahya.rickmortyapi.persistence;

abstract class _BaseEntityWithId_Test<ENTITY extends _BaseEntityWithId> extends _BaseEntity_Test<ENTITY, Integer> {

    _BaseEntityWithId_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Integer.class);
    }
}