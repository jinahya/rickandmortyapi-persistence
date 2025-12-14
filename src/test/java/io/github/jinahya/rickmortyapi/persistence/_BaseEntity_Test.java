package io.github.jinahya.rickmortyapi.persistence;

abstract class _BaseEntity_Test<ENTITY extends _BaseEntity, ID> extends _BaseEntity__<ENTITY, ID> {

    _BaseEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
