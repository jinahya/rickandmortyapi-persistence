package io.github.jinahya.rickmortyapi.persistence;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_Test<ENTITY extends __BaseEntity, ID> extends __BaseEntity__<ENTITY, ID> {

    _BaseEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }
}
