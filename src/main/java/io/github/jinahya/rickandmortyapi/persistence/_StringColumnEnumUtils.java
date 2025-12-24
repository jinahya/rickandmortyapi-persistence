package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

final class _StringColumnEnumUtils {

    static <
            E extends Enum<E> & _StringColumnEnum<E>
            >
    E valueOfColumnValue(@Nonnull final Class<E> enumClass, @Nonnull final String columnValue) {
        return __ColumnEnumUtils.valueOfColumnValue(enumClass, columnValue);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _StringColumnEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
