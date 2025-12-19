package io.github.jinahya.rickmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
abstract class _BaseEntity_Test<ENTITY extends _BaseEntity, ID> extends __Base_Test<ENTITY> {

    _BaseEntity_Test(final Class<ENTITY> typeClass, final Class<ID> idClass) {
        super(typeClass);
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<ID> idClass;
}
