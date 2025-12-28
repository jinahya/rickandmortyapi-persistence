package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

abstract class _StringColumnEnum_Test<E extends Enum<E> & __ColumnEnum<E, String>>
        extends __ColumnEnum_Test<E, String> {

    _StringColumnEnum_Test(final Class<E> enumClass) {
        super(enumClass, String.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void _AllConstantsExist_AllPredefinedColumnsValues() {
        assumeThat(columnValuesClass).isNotNull();
        final var allPredefinedColumnValues = _ColumnValues_TestUtils.getAllValues(columnValuesClass);
        final var enumSet = EnumSet.allOf(enumClass);
        for (final var c = new ArrayList<>(new HashSet<>(allPredefinedColumnValues)); !c.isEmpty(); ) {
            final var value = __ColumnEnumUtils.valueOfColumnValue(enumClass, c.removeFirst());
            enumSet.remove(value);
        }
        assertThat(enumSet).isEmpty();
    }

    // -----------------------------------------------------------------------------------------------------------------
    Class<? extends _ColumnValues> columnValuesClass;
}
