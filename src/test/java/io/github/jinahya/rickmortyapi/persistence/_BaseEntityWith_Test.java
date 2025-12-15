package io.github.jinahya.rickmortyapi.persistence;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntityWith_Test<ENTITY extends _BaseEntity> extends _BaseEntity_Test<ENTITY, Integer> {

    _BaseEntityWith_Test(final Class<ENTITY> entityClass) {
        super(entityClass, Integer.class);
    }
}
