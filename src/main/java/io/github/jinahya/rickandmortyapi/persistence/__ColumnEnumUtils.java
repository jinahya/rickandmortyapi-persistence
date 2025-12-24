package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class __ColumnEnumUtils {

    private static final Map<Class<?>, Map<Object, Enum<?>>> CACHE = new ConcurrentHashMap<>();

    public static <
            E extends Enum<E> & __ColumnEnum<E, T>,
            T
            >
    E valueOfColumnValue(@Nonnull final Class<E> enumClass, @Nonnull final T columnValue) {
        if (!Objects.requireNonNull(enumClass, "enumClass is null").isEnum()) {
            throw new IllegalArgumentException("not an enum: " + enumClass);
        }
        Objects.requireNonNull(columnValue, "columnValue is null");
        return enumClass.cast(
                CACHE.computeIfAbsent(enumClass, k -> new ConcurrentHashMap<>())
                     .computeIfAbsent(columnValue, k -> {
                         for (final var enumConstants : enumClass.getEnumConstants()) {
                             if (enumConstants.columnValue().equals(k)) {
                                 return enumConstants;
                             }
                         }
                         throw new IllegalArgumentException("no enum constant found for column value: " + k);
                     })
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __ColumnEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
