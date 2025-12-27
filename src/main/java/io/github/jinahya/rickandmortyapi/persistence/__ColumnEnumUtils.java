package io.github.jinahya.rickandmortyapi.persistence;

import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class for {@link __ColumnEnum}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class __ColumnEnumUtils {

    private static final Map<Class<?>, Map<Object, Enum<?>>> CACHE = new ConcurrentHashMap<>();

    static <
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
                            throw new IllegalArgumentException(
                                    "no enum constant found for column value: " + k + " in " + enumClass
                            );
                        })
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    private __ColumnEnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
