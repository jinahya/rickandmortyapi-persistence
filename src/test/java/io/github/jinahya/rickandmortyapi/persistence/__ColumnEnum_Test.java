package io.github.jinahya.rickandmortyapi.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
abstract class __ColumnEnum_Test<E extends Enum<E> & __ColumnEnum<E, T>, T> {

    @DisplayName("valueOfColumnValue(T)")
    @Test
    void valueOfColumnValue__() {
        try {
            final var method = enumClass.getDeclaredMethod("valueOfColumnValue", columnClass);
            if (!method.canAccess(null)) {
                method.setAccessible(true);
            }
            applyConstantsColumnValueStream(s -> {
                s.forEach(e -> {
                    final var enumConstant = e.getKey();
                    final var columnValue = e.getValue();
                    try {
                        assertThat(method.invoke(null, columnValue)).isSameAs(enumConstant);
                    } catch (final ReflectiveOperationException roe) {
                        throw new RuntimeException("failed to invoke " + method + " with " + columnValue, roe);
                    }
                });
                return null;
            });
        } catch (final ReflectiveOperationException roe) {
            log.debug("valueOfColumnValue(T) is not found on {}", enumClass, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    __ColumnEnum_Test(final Class<E> enumClass, final Class<T> columnClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
        this.columnClass = Objects.requireNonNull(columnClass, "columnClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    void _AllConstantsExist_AllPredefinedColumnsValues(final Collection<T> allPredefinedColumnValues) {
        Objects.requireNonNull(allPredefinedColumnValues, "allPredefinedColumnValues is null");
        final var enumSet = EnumSet.allOf(enumClass);
        for (final var c = new ArrayList<>(new HashSet<>(allPredefinedColumnValues)); !c.isEmpty(); ) {
            final var value = __ColumnEnumUtils.valueOfColumnValue(enumClass, c.removeFirst());
            enumSet.remove(value);
        }
        assertThat(enumSet).isEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("all constants' columns values; no nulls nor duplicates")
    @Test
    void _NoNullsNorDuplicates_AllConstantsColumnValues() {
        applyConstantsColumnValueStream(s -> {
            assertThat(s)
                    .doesNotContainNull()
                    .doesNotHaveDuplicates();
            return null;
        });
    }

    // ------------------------------------------------------------------------------------------------------- enumClass

    /**
     * Returns the result of the function applied with a stream all {@link Class#getEnumConstants() enum constants} of
     * the {@link #enumClass}.
     *
     * @param function the function to apply.
     * @param <R>      result type parameter.
     * @return the result of the function applied with a stream all {@link Class#getEnumConstants() enum constants} of
     *         the {@link #enumClass}.
     */
    final <R> R applyEnumConstantStream(final Function<? super Stream<E>, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return function.apply(Arrays.stream(enumClass.getEnumConstants()));
    }

    // ----------------------------------------------------------------------------------------------------- columnClass

    /**
     * Returns the result of the function applied with a stream all {@link __ColumnEnum#columnValue() column values} of
     * the {@link #enumClass}.
     *
     * @param function the function to apply.
     * @param <R>      result type parameter.
     * @return the result of the function applied with a stream all {@link __ColumnEnum#columnValue() column values} of
     *         the {@link #enumClass}.
     */
    final <R> R applyConstantsColumnValueStream(final Function<? super Stream<Map.Entry<E, T>>, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return applyEnumConstantStream(
                s -> function.apply(
                        s.map(e -> new AbstractMap.SimpleEntry<>(e, e.columnValue()))
                )
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<E> enumClass;

    final Class<T> columnClass;
}
