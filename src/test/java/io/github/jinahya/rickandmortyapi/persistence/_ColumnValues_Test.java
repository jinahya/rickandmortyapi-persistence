package io.github.jinahya.rickandmortyapi.persistence;

import java.util.Objects;

abstract class _ColumnValues_Test<T extends _ColumnValues> {

    _ColumnValues_Test(final Class<T> valuesClass) {
        super();
        this.valuesClass = Objects.requireNonNull(valuesClass, "valuesClass is null");
    }

    final Class<T> valuesClass;
}
