package io.github.jinahya.rickandmortyapi.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({
        "java:S115", // Constant names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class __ColumnEnumComparator_Test {

    private enum IntegerColumnEnum
            implements __ColumnEnum<IntegerColumnEnum, Integer> {

        _0,

        _1;

        @Override
        public Integer columnValue() {
            return ordinal();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("comparingColumnValue()")
    @Test
    void __() {
        final var comparator = __ColumnEnumComparator.<IntegerColumnEnum, Integer>comparingColumnValue();
        final var list = Arrays.asList(IntegerColumnEnum.values());
        assertThat(list).isSortedAccordingTo(comparator);
        assertThat(list.reversed()).isSortedAccordingTo(comparator.reversed());
    }

    // -----------------------------------------------------------------------------------------------------------------
    __ColumnEnumComparator_Test() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void compareTo__() {
        final var comparator = new __ColumnEnumComparator<IntegerColumnEnum, Integer>() {
        };
        final var list = Arrays.asList(IntegerColumnEnum.values());
        assertThat(list).isSortedAccordingTo(comparator);
        assertThat(list.reversed()).isSortedAccordingTo(comparator.reversed());
    }
}
