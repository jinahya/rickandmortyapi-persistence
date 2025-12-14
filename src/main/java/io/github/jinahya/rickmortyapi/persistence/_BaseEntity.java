package io.github.jinahya.rickmortyapi.persistence;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
abstract class _BaseEntity {

    protected _BaseEntity() {
        super();
    }
}
