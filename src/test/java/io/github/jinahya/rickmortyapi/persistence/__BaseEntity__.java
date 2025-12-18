package io.github.jinahya.rickmortyapi.persistence;

import java.util.Objects;

abstract class __BaseEntity__<ENTITY extends _BaseEntity, ID> {

    __BaseEntity__(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    final Class<ENTITY> entityClass;

    final Class<ID> idClass;
}
