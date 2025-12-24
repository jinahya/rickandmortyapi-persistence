package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

abstract class __ColumnEnum_Test<E extends Enum<E> & __ColumnEnum<E, T>, T> {

    __ColumnEnum_Test(final Class<E> enumClass, final Class<T> columnClass) {
        super();
        this.enumClass = Objects.requireNonNull(enumClass, "enumClass is null");
        this.columnClass = Objects.requireNonNull(columnClass, "columnClass is null");
    }

    @Test
    void __() {
        assertThat(Arrays.stream(enumClass.getEnumConstants()).map(__ColumnEnum::columnValue))
                .doesNotContainNull()
                .doesNotHaveDuplicates();
    }

    final Class<E> enumClass;

    final Class<T> columnClass;
}