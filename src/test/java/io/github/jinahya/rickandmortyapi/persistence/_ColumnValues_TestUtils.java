package io.github.jinahya.rickandmortyapi.persistence;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

final class _ColumnValues_TestUtils {

    /**
     * Returns an unmodifiable list of all {@code static final String} constants from the specified valuesClass.
     *
     * @param valuesClass the valuesClass.
     * @param <T>         values type parameter
     * @return an unmodifiable list of all {@code static final String} constants from the specified valuesClass.
     */
    static <T extends _ColumnValues> List<String> getAllValues(final Class<T> valuesClass) {
        return Arrays.stream(valuesClass.getDeclaredFields())
                .filter(f -> {
                    final var modifiers = f.getModifiers();
                    if (!Modifier.isStatic(modifiers)) {
                        return false;
                    }
                    if (!Modifier.isFinal(modifiers)) {
                        return false;
                    }
                    final var type = f.getType();
                    if (type != String.class) {
                        return false;
                    }
                    return true;
                })
                .map(f -> {
                    try {
                        return (String) f.get(null);
                    } catch (final IllegalAccessException iae) {
                        throw new RuntimeException(iae);
                    }
                })
                .toList();
    }

    private _ColumnValues_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
