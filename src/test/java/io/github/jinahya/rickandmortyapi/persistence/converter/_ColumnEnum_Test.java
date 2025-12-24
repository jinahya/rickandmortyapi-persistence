package io.github.jinahya.rickandmortyapi.persistence.converter;

import io.github.jinahya.rickandmortyapi.persistence._StringColumnEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class _ColumnEnum_Test<E extends Enum<E> & _StringColumnEnum<E>> {

    protected _ColumnEnum_Test(final Class<E> enumClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("all columnValue() does not contain null nor duplicates")
    @Test
    protected void _DoesNotContainNullNorDuplicates_AllColumnValues() {
        final var columnValues = Arrays.stream(enumClass.getEnumConstants()).map(_StringColumnEnum::columnValue).toList();
        assertThat(columnValues).doesNotContainNull().doesNotHaveDuplicates();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<E> enumClass;
}